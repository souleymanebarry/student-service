package com.barry.student.mappers;


import com.barry.student.dtos.StudentDto;
import com.barry.student.models.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {


    List<StudentDto> studentsToStudentDtos(List<Student> students);
    Student studentDtoToStudent(StudentDto studentDto);
    StudentDto studentToStudentDto(Student student);



}
