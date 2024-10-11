package org.adem.autotrade.dto.request;

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

    private String announcementName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime expireDate;
    private Long viewCount;
    private String description;

    private Status status;

    private CarResponseDto car;

}
