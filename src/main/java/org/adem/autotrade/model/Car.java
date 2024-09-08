package org.adem.autotrade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;
    private String model;
    private Integer year;
    private Long price;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_detail_id")
    private CarDetail carDetail;

    @ManyToMany
    @JoinTable(
            name = "car_advantage",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "advantage_id")
    )
    private List<Advantage> advantage;

    @OneToOne(mappedBy = "car")
    private Announcement announcement;
}
