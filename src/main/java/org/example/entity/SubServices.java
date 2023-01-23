package org.example.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Collection;

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

    String name;

    @ManyToOne(cascade = CascadeType.ALL)
    Services services;

    public SubServices(String name, Services services) {
        this.name = name;
        this.services = services;
    }
}
