package org.adem.autotrade.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@NamedEntityGraph(name = "car-graph",
        attributeNodes = {
                @NamedAttributeNode("advantage"),
        })
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;
    private String model;
    private Integer year;
    private Long price;

    @OneToOne
    @JoinColumn(name = "car_detail_id")
    private CarDetail carDetail;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "car_advantage",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "advantage_id")
    )
    private List<Advantage> advantage;

    @OneToOne(mappedBy = "car")
    @JsonIgnore
    @ToString.Exclude
    private Announcement announcement;
}
