package com.example.ShopForElectronicGoods.Exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException{
    private HttpStatus httpStatus;
    private int statusCode;

    public ApiRequestException(String message ) {
        super(message);
    }
    public ApiRequestException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;

    }
    public ApiRequestException(String message, HttpStatus httpStatus, int statusCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
