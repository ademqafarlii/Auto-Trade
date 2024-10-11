package org.adem.autotrade.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adem.autotrade.dto.request.UserRequestDto;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;
import org.adem.autotrade.dto.response.UserResponseDto;
import org.adem.autotrade.exception.EmailAlreadyInUseException;
import org.adem.autotrade.exception.InvalidCredentialsException;
import org.adem.autotrade.exception.UserNotFoundException;
import org.adem.autotrade.mapper.AnnouncementMapper;
import org.adem.autotrade.mapper.UserMapper;
import org.adem.autotrade.model.User;
import org.adem.autotrade.repository.UserRepository;
import org.adem.autotrade.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AnnouncementMapper announcementMapper;


    @Override
    public void register(UserRequestDto user) {
        Optional<User> userWithEmail = userRepository.findByEmail(user.getEmail());
        if (userWithEmail.isPresent()) {
            throw new EmailAlreadyInUseException("Email is already in use");
        }
        User user1 = userMapper.toUserEntity(user);
        userRepository.save(user1);
        log.info("User registered successfully with id:{}", user1.getId());

    }

    @Override
    public void updateCredentials(String email, String password, UserRequestDto user) {
        Optional<User> existingUser = userRepository.findByEmailAndPassword(email, password);
        if (existingUser.isEmpty()) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        existingUser.get().setName(user.getName());
        existingUser.get().setSurname(user.getSurname());
        existingUser.get().setEmail(user.getEmail());
        existingUser.get().setPassword(user.getPassword());
        existingUser.get().setPhoneNumber(user.getPhoneNumber());

        userRepository.save(existingUser.get());
        log.info("Credentials successfully updated with id:{}", existingUser.get().getId());

    }

    @Override
    public Set<AnnouncementResponseDto> getAllAnnouncementsOfUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with this id"));

        return user.getAnnouncements()
                .stream()
                .map(announcementMapper::toAnnouncementResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteAccount(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isEmpty()) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        userRepository.deleteByEmailAndPassword(email, password);
        log.info("Account successfully deleted with id:{}", user.get().getId());
    }

    @Override
    public Page<UserResponseDto> findBySpecification(String name,
                                                     String surname,
                                                     String email,
                                                     String phoneNumber,
                                                     Pageable pageable) {
        Specification<User> specification = null;

        specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (name != null) {
                predicateList.add(criteriaBuilder.equal(root.get("name"), name));
            }
            if (surname != null) {
                predicateList.add(criteriaBuilder.equal(root.get("surname"), surname));
            }
            if (email != null) {
                predicateList.add(criteriaBuilder.equal(root.get("email"), email));
            }
            if (phoneNumber != null) {
                predicateList.add(criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber));
            }

            Objects.requireNonNull(query).where(
                    criteriaBuilder.and(predicateList.toArray(predicateList.toArray(new Predicate[0])))
            );

            return query.getRestriction();
        };
        return userRepository.findAll(specification, pageable).map(userMapper::toUserResponse);
    }
}
