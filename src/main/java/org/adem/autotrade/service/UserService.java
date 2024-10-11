package org.adem.autotrade.service;

import org.adem.autotrade.dto.request.UserRequestDto;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;
import org.adem.autotrade.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface UserService {

    void register(UserRequestDto userRequestDto);

    void updateCredentials(String email, String password, UserRequestDto userRequestDto);

    Set<AnnouncementResponseDto> getAllAnnouncementsOfUser(Integer id);

    void deleteAccount(String email, String password);

    Page<UserResponseDto> findBySpecification(String name,
                                                     String surname,
                                                     String email,
                                                     String phoneNumber,
                                                     Pageable pageable);
}
