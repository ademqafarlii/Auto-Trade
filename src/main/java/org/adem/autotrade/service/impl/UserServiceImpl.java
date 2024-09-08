package org.adem.autotrade.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.adem.autotrade.exception.EmailAlreadyInUseException;
import org.adem.autotrade.exception.InvalidCredentialsException;
import org.adem.autotrade.exception.UserNotFoundException;
import org.adem.autotrade.model.Announcement;
import org.adem.autotrade.model.User;
import org.adem.autotrade.repository.UserRepository;
import org.adem.autotrade.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        Optional<User> userWithEmail = userRepository.findByEmail(user.getEmail());
        if (userWithEmail.isPresent()) {
            throw new EmailAlreadyInUseException("Email is already in use");
        }
        userRepository.save(user);
        log.info("User registered successfully with id:{}", user.getId());

    }

    @Override
    public void updateCredentials(String email, String password, User user) {
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
    public Set<Announcement> getAllAnnouncementsOfUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with this id"));
        return user.getAnnouncements();
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
}
