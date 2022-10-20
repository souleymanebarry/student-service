package com.barry.student.service;

import com.barry.student.dtos.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

    void addStudent(StudentDto studentDto);

    void deleteStudent(Long studentId);

    StudentDto getStudentById(Long studentId);
}
