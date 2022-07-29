package com.appsdeveloper.estore.productservice.command.rest;

import com.appsdeveloper.estore.productservice.core.entity.CreateProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    private final Environment environment;
    private final CommandGateway commandGateway;

    @Autowired
    public ProductCommandController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = getCreateProductCommand(createProductRestModel);
        String value;
        try {
            value = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception ex) {
            value = ex.getLocalizedMessage();
        }
        return value;
    }

    private CreateProductCommand getCreateProductCommand(CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .title(createProductRestModel.getTitle())
                .productId(UUID.randomUUID().toString()).build();
        return createProductCommand;
    }
}
