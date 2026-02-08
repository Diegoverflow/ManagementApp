package com.example.BarsAndRestaurantsApp.repositories;

import com.example.BarsAndRestaurantsApp.domain.entities.ProductEntity;
import com.example.BarsAndRestaurantsApp.domain.entities.entitiesEnums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, UUID>,
        ListPagingAndSortingRepository<ProductEntity,UUID> {

    Page<ProductEntity> findByProductCategory(Pageable pageable, ProductCategory productCategory);

    @Query("""
        select distinct p.productCategory
        from ProductEntity p
    """)
    List<ProductCategory> findDistinctProductCategories();
}
