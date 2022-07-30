package com.appsdeveloper.estore.productservice;

import com.appsdeveloper.estore.productservice.command.interceptor.CreateProductCommandInterceptor;
import com.appsdeveloper.estore.productservice.core.exceptionhandling.ProductServiceEventsExceptionHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Autowired
    public void registerCreateProductCommandInterceptor(ApplicationContext applicationContext, CommandBus commandBus) {
        commandBus.registerDispatchInterceptor(applicationContext.getBean(CreateProductCommandInterceptor.class));
    }

    @Autowired
    public void configureGroupWithEventsExceptionHandler(EventProcessingConfigurer configurer) {
        configurer.registerListenerInvocationErrorHandler("product-group",
                conf -> new ProductServiceEventsExceptionHandler());
    }
}