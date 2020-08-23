package com.exalt.partssystem.error;

import com.exalt.partssystem.error.exceptions.BadRequestExceptions;
import com.exalt.partssystem.error.exceptions.ConflictExceptions;
import com.exalt.partssystem.error.exceptions.InvalidHeaderFieldException;
import com.exalt.partssystem.error.exceptions.NotFoundExceptions;
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
     handling te exception in specific method
     */
    @ExceptionHandler(NotFoundExceptions.class)
    public ResponseEntity<ErrorFeatures> handleApiException(NotFoundExceptions ex, WebRequest request) {
        logger.info("ApiExceptionsHandler class");
        ErrorFeatures error = new ErrorFeatures(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, ex.getStatus());
    }

    /*
     handling the exception in specific method
     */
    @ExceptionHandler(ConflictExceptions.class)
    public ResponseEntity<ErrorFeatures> handleApiException(ConflictExceptions ex, WebRequest request) {
        logger.info("ApiExceptionsHandler class");
        ErrorFeatures error = new ErrorFeatures(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, ex.getStatus());
    }

    /*
     handling the exception in specific method
     */
    @ExceptionHandler(  BadRequestExceptions.class)
    public ResponseEntity<ErrorFeatures> handleApiException(BadRequestExceptions ex, WebRequest request) {
        logger.info("ApiExceptionsHandler class");
        ErrorFeatures error = new ErrorFeatures(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, ex.getStatus());
    }
    @ExceptionHandler
    public ResponseEntity<ErrorFeatures> handleApiException(InvalidHeaderFieldException ex, WebRequest request) {
        logger.info("ApiExceptionsHandler class");
        ErrorFeatures error = new ErrorFeatures(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, ex.getStatus());
    }
}
