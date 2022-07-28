package com.appsdeveloper.estore.productservice.query;

import com.appsdeveloper.estore.productservice.core.data.ProductEntity;
import com.appsdeveloper.estore.productservice.core.repository.ProductsRepository;
import com.appsdeveloper.estore.productservice.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsQueryHandler {
    private final ProductsRepository productsRepository;

    public ProductsQueryHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductQuery query) {
        List<ProductRestModel> list = new ArrayList<>();
        List<ProductEntity> getProducts = productsRepository.findAll();
        for (ProductEntity productEntity : getProducts) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            list.add(productRestModel);
        }
        return list;
    }
}
