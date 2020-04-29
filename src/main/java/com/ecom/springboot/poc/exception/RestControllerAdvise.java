package com.ecom.springboot.poc.exception;

import com.ecom.springboot.poc.model.OrderRetrieveResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@NoArgsConstructor
public class RestControllerAdvise {

  @ExceptionHandler(InvalidInputException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public OrderRetrieveResponse handleInvalidInputException(InvalidInputException ex){
    return getErrorResponse(ex,404);
  }

  private OrderRetrieveResponse getErrorResponse(RuntimeException exception, Integer code) {
    return OrderRetrieveResponse.builder()
                          .source("ecom-springboot-poc")
                          .status(code)
                          .message(exception.getMessage()).build();
  }




}
