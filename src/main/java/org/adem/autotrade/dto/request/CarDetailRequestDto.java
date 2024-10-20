package org.adem.autotrade.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.adem.autotrade.dto.response.CarResponseDto;
import org.adem.autotrade.enums.Fuel;
import org.adem.autotrade.enums.Situation;
import org.adem.autotrade.enums.Transmission;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDetailRequestDto {
    private Long mileage;

    private Transmission transmission;

    @NotNull
    private Boolean isNew;

    private String vanType;
    private String color;
    private Situation situation;
    private Integer ownerCount;

    private String vinCode;
    private String description;

    private Fuel fuelType;

    private Long enginePower;


    private CarResponseDto car;
}
