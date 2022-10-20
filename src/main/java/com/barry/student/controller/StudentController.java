package com.barry.student.controller;


import com.barry.student.dtos.StudentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/students")
public interface StudentController {


    @GetMapping
    ResponseEntity<List<StudentDto>> getAllStudents();

    @PostMapping(produces="application/json", consumes = "application/json")
    ResponseEntity<Void> saveStudent(@RequestBody StudentDto studentDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteStudentById(@PathVariable(name = "id") Long studentId);

}
