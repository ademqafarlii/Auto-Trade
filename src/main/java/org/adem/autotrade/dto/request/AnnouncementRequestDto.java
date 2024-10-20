package org.adem.autotrade.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adem.autotrade.dto.response.CarResponseDto;
import org.adem.autotrade.enums.Status;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementRequestDto {

    @NotBlank(message = "announcement name cannot be blank")
    private String announcementName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime expireDate;
    private Long viewCount;
    private String description;

    @NotNull
    private Status status;

    private CarResponseDto car;

}
