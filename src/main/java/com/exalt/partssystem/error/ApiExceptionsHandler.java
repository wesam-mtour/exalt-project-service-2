package com.exalt.partssystem.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 write global code shared to all controllers, handle exceptions across the whole application
 */
@ControllerAdvice
/*
 ApiExceptionsHandler extends from  ResponseEntityExceptionHandler  because ResponseEntityExceptionHandler has many handles
 */
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionsHandler.class);

    /*
     handling exceptions in specific handler
     */
    @ExceptionHandler(NotFoundExceptions.class)
    public ResponseEntity<ErrorFeatures> handleApiException(NotFoundExceptions ex, WebRequest request) {
        logger.info("ApiExceptionsHandler class");
        ErrorFeatures error = new ErrorFeatures(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, ex.getStatus());
    }
}
