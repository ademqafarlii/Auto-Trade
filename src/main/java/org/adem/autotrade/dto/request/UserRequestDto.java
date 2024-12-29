package org.adem.autotrade.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;

import java.util.List;

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

    private List<AnnouncementResponseDto> announcements;

}
