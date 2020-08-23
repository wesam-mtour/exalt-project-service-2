package com.exalt.partssystem.error.exceptions;

import com.exalt.partssystem.error.ApiListExceptions;
import org.springframework.http.HttpStatus;

public class InvalidHeaderFieldException extends ApiListExceptions {

    public InvalidHeaderFieldException(String message) {
        super(message);
    }
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.PRECONDITION_FAILED;
    }
}
