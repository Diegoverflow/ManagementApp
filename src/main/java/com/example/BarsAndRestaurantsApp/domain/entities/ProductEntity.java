package com.example.BarsAndRestaurantsApp.domain.entities;

import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.Allergen;
import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Getter
@Setter
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

    @Column(nullable = false, unique = true)
    private String titleIt;

    @Column  //(unique = true)
    private String titleEn;//

    @Column
    private String descriptionIt;

    @Column
    private String descriptionEn;

    @Column
    private String ingredientsIt;

    @Column
    private String ingredientsEn;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @ElementCollection(targetClass = Allergen.class)
    @CollectionTable(
            name = "allergens",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "allergen")
    private Set<Allergen> allergens = new HashSet<>();

    @Column
    private Boolean veganOk = false;

    @Column(nullable = false)
    private Float price;

    private Integer inStock;

    @Column(nullable = false)
    private String imageName;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
