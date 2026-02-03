package com.example.BarsAndRestaurantsApp.domain.entities;

import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String titleIt;

    private String titleEn;

    private String descriptionIt;

    private String descriptionEn;

    private String IngredientsIt;

    private String IngredientsEn;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    private Float price;

    private Integer inStock;

    private String imageName;


}
