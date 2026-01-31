package com.example.BarsAndRestaurantsApp.domain.dtos;

import com.example.BarsAndRestaurantsApp.domain.entities.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableDto {

    private Integer id;

    private Set<TicketEntity> tickets = null;

}
