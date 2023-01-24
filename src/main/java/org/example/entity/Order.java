package org.example.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    String description;
    @Column(name = "proposed_price")
    Long proposedPrice;
    @Column(name = "start_date")
    Date startDate;
    String address;
    //todo add status or somting like this
//    boolean status;

    public Order(String description, Long proposedPrice, Date startDate, String address) {
        this.description = description;
        this.proposedPrice = proposedPrice;
        this.startDate = startDate;
        this.address = address;
    }
}
