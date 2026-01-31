package com.example.BarsAndRestaurantsApp.domain.dtos;

import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private UUID id;

    private TicketEntity ticket;

    private Integer productQuantity;

    private ProductEntity product;

}
