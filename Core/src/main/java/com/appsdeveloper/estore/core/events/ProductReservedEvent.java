package com.appsdeveloper.estore.core.events;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductReservedEvent {
    private final String productId;
    private final int quantity;
    private final String orderId;
    private final String userId;
}
