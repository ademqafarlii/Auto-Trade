package org.adem.autotrade.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvantageResponseDto {

    private Boolean abs;
    private Boolean luke;
    private Boolean rainSensor;
    private Boolean centralLock;
    private Boolean parkingSensors;
    private Boolean airConditioner;
    private Boolean heatedSeats;
}
