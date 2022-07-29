package com.appsdeveloper.estore.productservice.command.interceptor;

import com.appsdeveloper.estore.productservice.command.rest.CreateProductCommand;
import com.appsdeveloper.estore.productservice.core.data.ProductLookupEntity;
import com.appsdeveloper.estore.productservice.core.repository.ProductLookupRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);
    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            LOGGER.info("Interceptor command" + command.getPayloadType());
            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
                ProductLookupEntity productLookupEntity = productLookupRepository.
                        findProductLookupEntityByProductIdOrTitle(createProductCommand.getProductId(),
                                createProductCommand.getTitle());
                matchTheProduct(createProductCommand, productLookupEntity);
            }
            return command;
        };
    }

    private void matchTheProduct(CreateProductCommand createProductCommand, ProductLookupEntity productLookupEntity) {
        if (productLookupEntity != null) {
            throw new IllegalStateException(String.format("Product with productId %s or title %s already exist",
                    createProductCommand.getProductId(), createProductCommand.getTitle()));
        }
    }
}
