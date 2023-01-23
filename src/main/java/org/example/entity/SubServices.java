package org.example.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "services")
public class SubServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToMany(cascade = CascadeType.ALL)
    Collection<Services> services;



}
