package com.appsdeveloper.estore.productservice.core.exceptionhandling;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

public class ProductServiceEventsExceptionHandler implements ListenerInvocationErrorHandler {
    @Override
    public void onError(Exception exception, EventMessage<?> eventMessage, EventMessageHandler eventMessageHandler) throws Exception {
        throw exception;
    }
}
