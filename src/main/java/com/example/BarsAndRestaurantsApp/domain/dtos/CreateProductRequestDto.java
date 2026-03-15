package com.example.BarsAndRestaurantsApp.domain.dtos;

import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.Allergen;
import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import jakarta.validation.constraints.*;
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
    @Pattern(
            regexp = "^[A-Za-zÀ-ÿ ]+$",
            message = "Title IT can only contain letters and spaces"
    )
    private String titleIt;

    @Size(max = 20, message = "A title can contain {max} characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ÿ ]+$",
            message = "Title EN can only contain letters and spaces"
    )
    private String titleEn;

    @Size(max = 200, message = "A description can contain {max} characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ÿ0-9 .,;:!?()'\"\\-\\n\\r]+$",
            message = "Description ITcontains invalid characters"
    )
    private String descriptionIt;

    @Size(max = 200, message = "A description can contain {max} characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ÿ0-9 .,;:!?()'\"\\-\\n\\r]+$",
            message = "Description EN contains invalid characters"
    )
    private String descriptionEn;

    @NotBlank(message = "Ingredients are required")
    @Size(max = 50, message = "Ingredients IT can contain {max} characters")
    @Pattern(
            regexp = "^[a-zA-ZÀ-ÿ ,]+$",
            message = "Ingredients can only contain letters, spaces and commas"
    )
    private String ingredientsIt;

    @NotBlank(message = "Ingredients are required")
    @Size(max = 50, message = "Ingredients EN can contain {max} characters")
    @Pattern(
            regexp = "^[a-zA-ZÀ-ÿ ,]+$",
            message = "Ingredients can only contain letters, spaces and commas"
    )
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
