package org.adem.autotrade.dto.request;


import lombok.*;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;

    private Set<AnnouncementResponseDto> announcements;
}
