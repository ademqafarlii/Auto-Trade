package org.adem.autotrade.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvantageRequestDto {

    private Boolean abs;
    private Boolean luke;
    private Boolean rainSensor;
    private Boolean centralLock;
    private Boolean parkingSensors;
    private Boolean airConditioner;
    private Boolean heatedSeats;
}
