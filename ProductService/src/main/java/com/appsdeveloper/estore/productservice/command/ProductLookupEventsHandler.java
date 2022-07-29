package com.appsdeveloper.estore.productservice.command;

import com.appsdeveloper.estore.productservice.core.data.ProductLookupEntity;
import com.appsdeveloper.estore.productservice.core.events.ProductCreatedEvent;
import com.appsdeveloper.estore.productservice.core.repository.ProductLookupRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {
    private final ProductLookupRepository productLookupRepository;

    @Autowired
    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductLookupEntity productLookupEntity =
                new ProductLookupEntity(event.getProductId(), event.getTitle());
        productLookupRepository.save(productLookupEntity);
    }
}
