package com.example.BarsAndRestaurantsApp.domain.dtos;

import com.example.BarsAndRestaurantsApp.domain.entities.BarRestaurantTableEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.CustomerOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {

    private UUID id;

    private LocalDateTime date;

    private boolean open;

    private BarRestaurantTableEntity ticketTable;

    private Set<CustomerOrderEntity> orders = null;

}
