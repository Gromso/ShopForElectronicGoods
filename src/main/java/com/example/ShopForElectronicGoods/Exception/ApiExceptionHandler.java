package com.example.ShopForElectronicGoods.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception) {
       HttpStatus bafRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException =  new ApiException(
                exception.getMessage(),
                bafRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

       return new ResponseEntity<Object>(apiException, bafRequest);

    }
}
