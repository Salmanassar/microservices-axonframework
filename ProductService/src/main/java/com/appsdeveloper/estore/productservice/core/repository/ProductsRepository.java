package com.appsdeveloper.estore.productservice.core.repository;

import com.appsdeveloper.estore.productservice.core.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findProductEntityByProductId(String productId);

    ProductEntity findProductEntitiesByProductIdOrTitle(String productId, String title);
}
