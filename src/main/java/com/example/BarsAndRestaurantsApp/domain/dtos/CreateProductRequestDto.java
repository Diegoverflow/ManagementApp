package com.example.BarsAndRestaurantsApp.domain.dtos;

import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.Allergen;
import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class CreateProductRequestDto {


    @NotBlank(message = "Title is required")
    @Size(max = 20, message = "A title can contain {max} characters")
    private String titleIt;

    @Size(max = 20, message = "A title can contain {max} characters")
    private String titleEn;

    @Size(max = 200, message = "A description can contain {max} characters")
    private String descriptionIt;

    @Size(max = 200, message = "A description can contain {max} characters")
    private String descriptionEn;

    @NotBlank(message = "Ingredients are required")
    @Size(max = 50, message = "Ingredients can contain {max} characters")
    private String ingredientsIt;

    @NotBlank(message = "Ingredients are required")
    @Size(max = 50, message = "Ingredients can contain {max} characters")
    private String ingredientsEn;

    @NotNull(message = "Product category is required")
    private ProductCategory productCategory;

    @Builder.Default
    @Size(max = 13, message = "Maximum {max} allergens allowed")
    private Set<Allergen> allergens = new HashSet<>();

    @NotNull(message = "Vegan flag must be specified")
    private Boolean veganOk;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Float price;

    private Integer inStock;

    private String imageName;

}
