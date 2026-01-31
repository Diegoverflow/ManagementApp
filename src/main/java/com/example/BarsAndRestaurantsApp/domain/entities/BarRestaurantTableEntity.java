package com.example.BarsAndRestaurantsApp.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bar_restaurant_tables")
public class BarRestaurantTableEntity {

    @Id
    private Integer id;

}
