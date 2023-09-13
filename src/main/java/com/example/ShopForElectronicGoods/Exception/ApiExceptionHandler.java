package com.example.ShopForElectronicGoods.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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


    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().toString();
        List<String> defaultMessages = extractDefaultMessages(errors);
        String combinedMessages = String.join(", ", defaultMessages);
        ApiExceptionValid apiExceptionValid = new ApiExceptionValid(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(ZoneId.of("Z")),
                combinedMessages
        );

        return new ResponseEntity<>(apiExceptionValid, HttpStatus.BAD_REQUEST);
    }

    private List<String> extractDefaultMessages(String errors) {
        List<String> defaultMessages = new ArrayList<>();
        Pattern pattern = Pattern.compile("default message \\[(.*?)\\]");
        Matcher matcher = pattern.matcher(errors);

        while (matcher.find()) {
            String defaultMessage = matcher.group(1);
            defaultMessages.add(defaultMessage);
        }

        return defaultMessages;
    }*/
}
