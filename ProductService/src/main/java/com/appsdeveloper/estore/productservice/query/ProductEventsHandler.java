package com.appsdeveloper.estore.productservice.query;

import com.appsdeveloper.estore.productservice.core.data.ProductEntity;
import com.appsdeveloper.estore.productservice.core.events.ProductCreateEvent;
import com.appsdeveloper.estore.productservice.core.repository.ProductsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {
    private ProductsRepository productsRepository;

    @Autowired
    public ProductEventsHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @EventHandler
    public void on(ProductCreateEvent productCreateEvent) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreateEvent, productEntity);
        productsRepository.save(productEntity);
    }
}
