package org.adem.autotrade.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "surname cannot be blank")
    private String surname;

    @NotBlank(message = "email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "phone number cannot be blank")
    private String phoneNumber;

    private Set<AnnouncementResponseDto> announcements;
}
