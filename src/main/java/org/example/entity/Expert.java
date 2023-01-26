package org.example.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.Enum.Role;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class Expert extends User {
    public Expert(String firstName, String lastName, String email, LocalDate date, String password, Role role, boolean status, Long inventory, String ability) {
        super(firstName, lastName, email, date, password, role, status, inventory);
    }

    public Expert(String firstName, String lastName, String email, LocalDate date, String password, Role role, boolean status, Long inventory, Services services, SubServices subServices) {
        super(firstName, lastName, email, date, password, role, status, inventory);
        this.services = services;
        this.subServices = subServices;
    }
    @ManyToOne
    Services services;

    @ManyToOne
    SubServices subServices;

}
