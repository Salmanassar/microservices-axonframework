package com.appsdeveloper.estore.productservice.core.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionMessage {
    private final Date timestamp;
    private final String message;
}
