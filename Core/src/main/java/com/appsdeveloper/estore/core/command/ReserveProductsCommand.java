package com.appsdeveloper.estore.core.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@Builder
public class ReserveProductsCommand {

    @TargetAggregateIdentifier
    private final String productId;
    private final int quantity;
    private final String orderId;
    private final String userId;


}
