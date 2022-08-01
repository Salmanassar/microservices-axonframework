package com.appsdeveloper.estore.productservice.command;

import com.appsdeveloper.estore.core.command.ReserveProductsCommand;
import com.appsdeveloper.estore.core.events.ProductReservedEvent;
import com.appsdeveloper.estore.productservice.command.rest.CreateProductCommand;
import com.appsdeveloper.estore.productservice.core.events.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductRestModel) {
        checkingPriceAndTitle(createProductRestModel);
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductRestModel, productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @CommandHandler
    public void handle(ReserveProductsCommand reserveProductsCommand) {
        if (quantity < reserveProductsCommand.getQuantity()) {
            throw new IllegalArgumentException("Insufficient number of items in the stock");
        }
        ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
                .orderId(reserveProductsCommand.getOrderId())
                .productId(reserveProductsCommand.getProductId())
                .quantity(reserveProductsCommand.getQuantity())
                .userId(reserveProductsCommand.getUserId()).build();
        AggregateLifecycle.apply(productReservedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.getProductId();
        this.title = productCreatedEvent.getTitle();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }
    @EventSourcingHandler
    public void on(ProductReservedEvent productReservedEvent){
        this.quantity-=productReservedEvent.getQuantity();
    }

    private void checkingPriceAndTitle(CreateProductCommand createProductRestModel) {
        if (createProductRestModel.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price can not be less or equal than zero");
        }
        if (createProductRestModel.getTitle() == null
                || createProductRestModel.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }
}
