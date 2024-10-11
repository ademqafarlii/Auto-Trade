package org.adem.autotrade.dto.request;


import lombok.*;
import org.adem.autotrade.dto.response.AdvantageResponseDto;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;
import org.adem.autotrade.dto.response.CarDetailResponseDto;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequestDto {

    private String brand;
    private String model;
    private Integer year;
    private Long price;

    private CarDetailResponseDto carDetail;

    private List<AdvantageResponseDto> advantage;

    private AnnouncementResponseDto announcement;
}
