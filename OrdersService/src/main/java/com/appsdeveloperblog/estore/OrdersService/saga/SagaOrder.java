package com.appsdeveloperblog.estore.OrdersService.saga;

import com.appsdeveloper.estore.core.command.ReserveProductsCommand;
import com.appsdeveloper.estore.core.events.ProductReservedEvent;
import com.appsdeveloperblog.estore.OrdersService.core.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class SagaOrder {
    private transient CommandGateway commandGateway;
    private static final Logger LOGGER = LoggerFactory.getLogger(SagaOrder.class);

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        ReserveProductsCommand reserveProductsCommand = ReserveProductsCommand.builder()
                .orderId(orderCreatedEvent.getOrderId())
                .productId(orderCreatedEvent.getProductId())
                .quantity(orderCreatedEvent.getQuantity())
                .userId(orderCreatedEvent.getUserId()).build();
        LOGGER.info("OrderCreatedEvent handled for orderId " + reserveProductsCommand.getOrderId()
                + " and productId " + reserveProductsCommand.getProductId());
        commandGateway.send(reserveProductsCommand, new CommandCallback<ReserveProductsCommand, Object>() {

            @Override
            public void onResult(CommandMessage<? extends ReserveProductsCommand> commandMessage,
                                 CommandResultMessage<? extends Object> commandResultMessage) {
                if (commandResultMessage.isExceptional()) {

                }
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(ProductReservedEvent productReservedEvent) {
        LOGGER.info("OrderCreatedEvent handled for orderId " + productReservedEvent.getOrderId()
                + " and productId " + productReservedEvent.getProductId());
    }
}
