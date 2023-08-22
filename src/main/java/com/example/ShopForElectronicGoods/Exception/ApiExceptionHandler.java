package com.example.ShopForElectronicGoods.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception) {
        ApiException apiException =  new ApiException(
                exception.getMessage(),
                exception.getHttpStatus(),
                exception.getStatusCode(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

       return new ResponseEntity<Object>(apiException, exception.getHttpStatus());

    }

}
