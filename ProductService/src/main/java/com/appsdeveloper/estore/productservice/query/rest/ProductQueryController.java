package com.appsdeveloper.estore.productservice.query.rest;

import com.appsdeveloper.estore.productservice.query.FindProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    private final QueryGateway queryGateway;

    @Autowired
    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getProduct() {
        FindProductQuery findProductQuery = new FindProductQuery();
        return queryGateway.query(findProductQuery,
                ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
    }

}
