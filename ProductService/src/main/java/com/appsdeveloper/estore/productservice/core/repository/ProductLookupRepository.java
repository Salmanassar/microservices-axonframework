package com.appsdeveloper.estore.productservice.core.repository;

import com.appsdeveloper.estore.productservice.core.data.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
    ProductLookupEntity findProductLookupEntityByProductIdOrTitle(String productId, String title);
}
