package org.example.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.Enum.JobStatus;

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

    @ManyToOne
    User user;

    String description;

    @Column(name = "proposed_price")
    Long proposedPrice;

    @Column(name = "start_date")
    String startDate;
    String address;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    JobStatus jobStatus;

    String offer;

    String opinion;

    @ManyToOne
    Services services;

    @ManyToOne
    SubServices subServices;

    public Order(User user, String description, Long proposedPrice, String startDate, String address, JobStatus jobStatus, Services services, SubServices subServices) {
        this.user = user;
        this.description = description;
        this.proposedPrice = proposedPrice;
        this.startDate = startDate;
        this.address = address;
        this.jobStatus = jobStatus;
        this.services = services;
        this.subServices = subServices;
    }
}