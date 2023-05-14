package com.jamesaworo.stocky.features.product.data.repository;

import com.jamesaworo.stocky.features.product.domain.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aworo James
 * @since 5/10/23
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}