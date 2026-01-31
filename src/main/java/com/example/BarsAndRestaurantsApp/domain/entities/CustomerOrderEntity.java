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
@Table(name = "customer_orders")
public class CustomerOrderEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private Integer productQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;



}
