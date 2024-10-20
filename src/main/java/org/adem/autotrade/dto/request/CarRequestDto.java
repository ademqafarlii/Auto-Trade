package org.adem.autotrade.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "brand cannot be blank")
    private String brand;
    @NotBlank(message = "model cannot be blank")
    private String model;
    @NotNull
    private Integer year;
    @NotNull
    private Long price;

    private CarDetailResponseDto carDetail;

    private List<AdvantageResponseDto> advantage;

    private AnnouncementResponseDto announcement;
}
