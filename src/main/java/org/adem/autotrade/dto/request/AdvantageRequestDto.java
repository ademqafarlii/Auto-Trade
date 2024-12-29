package org.adem.autotrade.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvantageRequestDto {

    @NotNull
    private Boolean abs;
    @NotNull
    private Boolean luke;
    @NotNull
    private Boolean rainSensor;
    @NotNull
    private Boolean centralLock;
    @NotNull
    private Boolean parkingSensors;
    @NotNull
    private Boolean airConditioner;
    @NotNull
    private Boolean heatedSeats;
}
