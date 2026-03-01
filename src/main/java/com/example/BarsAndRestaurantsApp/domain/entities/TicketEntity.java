package com.example.BarsAndRestaurantsApp.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
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

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private boolean open = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bar_restaurant_table_id", nullable = false)
    private BarRestaurantTableEntity ticketTable;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerOrderEntity> orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketEntity)) return false;
        return id != null && id.equals(((TicketEntity) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
