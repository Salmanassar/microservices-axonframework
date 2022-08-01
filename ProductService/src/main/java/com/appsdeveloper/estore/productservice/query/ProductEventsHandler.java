package com.appsdeveloper.estore.productservice.query;

import com.appsdeveloper.estore.core.events.ProductReservedEvent;
import com.appsdeveloper.estore.productservice.core.data.ProductEntity;
import com.appsdeveloper.estore.productservice.core.events.ProductCreatedEvent;
import com.appsdeveloper.estore.productservice.core.repository.ProductsRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {
    private ProductsRepository productsRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventsHandler.class);

    @Autowired
    public ProductEventsHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);
        try {
            productsRepository.save(productEntity);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
//         It is for checking Event Handler and how it works exception handler
//        if(true) throw new Exception("Forcing exception in the Event Handler class");

    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        ProductEntity productEntity = productsRepository
                .findProductEntityByProductId(productReservedEvent.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
        productsRepository.save(productEntity);
        LOGGER.info("ProductReservedEvent handled for orderId " + productReservedEvent.getOrderId()
                + " and productId " + productReservedEvent.getProductId());
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handler(IllegalArgumentException exception) {
        throw exception;
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handler(Exception exception) throws Exception {
        throw exception;
    }


}
