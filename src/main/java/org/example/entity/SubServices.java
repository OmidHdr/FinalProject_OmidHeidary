package org.example.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "sub_services")
public class SubServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String name;

    @ManyToOne(cascade = CascadeType.ALL)
    Services services;

    String description;

    @Column(name = "base_price")
    Long basePrice;

    public SubServices(String name, Services services, String description, Long basePrice) {
        this.name = name;
        this.services = services;
        this.description = description;
        this.basePrice = basePrice;
    }
}
