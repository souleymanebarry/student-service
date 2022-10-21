package com.barry.student.config;

import com.barry.student.dtos.ErrorDto;
import com.barry.student.exceptions.BadRequestException;
import com.barry.student.exceptions.StudentNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { BadRequestException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(BadRequestException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ErrorDto.builder().message(ex.getMessage()).build());
    }

    @ExceptionHandler(value = { StudentNotFoundException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto> handleStudentNotFoundException(StudentNotFoundException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ErrorDto.builder().message(ex.getMessage()).build());
    }


}
