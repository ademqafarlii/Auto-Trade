package org.adem.autotrade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Advantage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean abs;
    private Boolean luke;
    private Boolean rainSensor;
    private Boolean centralLock;
    private Boolean parkingSensors;
    private Boolean airConditioner;
    private Boolean heatedSeats;
}
