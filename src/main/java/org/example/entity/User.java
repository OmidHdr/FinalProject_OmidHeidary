package org.example.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.Enum.Role;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    String username;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false,name = "first_name")
    String firstName;
    @Column(nullable = false,name = "last_name")
    String lastName;
    @Column(nullable = false,unique = true)
    String email;
    @Column(nullable = false)
    LocalDate date;
    @Column(nullable = false)
    String password;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    Role role;
    boolean status;
    Long inventory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, LocalDate date,String password,Role role,boolean status,Long inventory) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.date = date;
        this.password = password;
        this.role = role;
        this.status = status;
        this.inventory = inventory;
        this.username = email;
    }

}
