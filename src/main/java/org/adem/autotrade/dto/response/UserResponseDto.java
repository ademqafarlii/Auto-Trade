package org.adem.autotrade.dto.response;


import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;

    private Set<AnnouncementResponseDto> announcements;
}
