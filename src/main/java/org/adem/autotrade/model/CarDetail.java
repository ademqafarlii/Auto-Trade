package org.adem.autotrade.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.adem.autotrade.enums.Fuel;
import org.adem.autotrade.enums.Situation;
import org.adem.autotrade.enums.Transmission;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CarDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long mileage;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    private Boolean isNew;

    private String vanType;
    private String color;
    @Enumerated(EnumType.STRING)
    private Situation situation;
    private Integer ownerCount;

    @Column(unique = true)
    private String vinCode;
    private String description;

    @Enumerated(EnumType.STRING)
    private Fuel fuelType;

    private Long enginePower;

    @OneToOne(mappedBy = "carDetail")
    @JsonIgnore
    @ToString.Exclude
    private Car car;
}
