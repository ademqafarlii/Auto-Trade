package org.adem.autotrade.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarResponseDto {
    private String brand;
    private String model;
    private Integer year;
    private Long price;

    private CarDetailResponseDto carDetail;

    private List<AdvantageResponseDto> advantage;

    private AnnouncementResponseDto announcement;
}
