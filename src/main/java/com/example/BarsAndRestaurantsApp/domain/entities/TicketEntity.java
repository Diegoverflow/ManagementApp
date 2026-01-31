package com.example.BarsAndRestaurantsApp.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private LocalDateTime date;

    private boolean open;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bar_restaurant_table_id", nullable = false)
    private BarRestaurantTableEntity ticketTable;

    @OneToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    private Set<CustomerOrderEntity> orders = new HashSet<>();


}
