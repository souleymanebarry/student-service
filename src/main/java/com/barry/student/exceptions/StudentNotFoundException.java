package com.barry.student.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Slf4j
public class StudentNotFoundException extends RuntimeException {
    private final HttpStatus status;

    public StudentNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        log.error(message);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
