package org.adem.autotrade.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adem.autotrade.enums.Status;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementResponseDto {

    private String announcementName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime expireDate;
    private Long viewCount;
    private String description;

    private Status status;

    private CarResponseDto car;

}
