package com.example.BarsAndRestaurantsApp.domain.dtos;

import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.Allergen;
import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private UUID id;

    private String titleIt;

    private String titleEn; //

    private String descriptionIt;

    private String descriptionEn;

    private String ingredientsIt;

    private String ingredientsEn;

    private ProductCategory productCategory;

    private Set<Allergen> allergens = null;

    private Boolean veganOk;

    private Float price;

    private Integer inStock;

    private String imageName;

}
