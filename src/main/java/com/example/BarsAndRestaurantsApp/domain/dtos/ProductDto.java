package com.example.BarsAndRestaurantsApp.domain.dtos;

import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private UUID id;

    private String title;

    private String description;

    private ProductCategory productCategory;

    private Float price;

    private Integer inStock;

    private String imageName;

}
