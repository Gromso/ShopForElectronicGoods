package com.example.ShopForElectronicGoods.Exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final int statusCode;
    private final ZonedDateTime timestamp;



    public ApiException(String message, HttpStatus httpStatus, int statusCode, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
