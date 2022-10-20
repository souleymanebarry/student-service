package com.barry.student.service.impl;

import com.barry.student.dtos.StudentDto;
import com.barry.student.exceptions.BadRequestException;
import com.barry.student.exceptions.StudentNotFoundException;
import com.barry.student.mappers.StudentMapper;
import com.barry.student.models.Student;
import com.barry.student.repositories.StudentRepository;
import com.barry.student.service.StudentService;
import com.barry.student.utils.MappingUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        final List<Student> studentList = studentRepository.findAll();
        return studentMapper.studentsToStudentDtos(studentList);
    }

    @Override
    public void addStudent(StudentDto studentDto) {
        boolean existsEmail = studentRepository.selectExistsEmail(studentDto.getEmail());
        if(existsEmail)
            throw new BadRequestException(String.format("Email: '%s' is taken", studentDto.getEmail()));
        final Student student = studentMapper.studentDtoToStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id))
            throw new StudentNotFoundException(String.format("Student with id: '%s' does not exists", id));
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        MappingUtils.checkIfValidateId(id);
        Student student = getById(id);
        return studentMapper.studentToStudentDto(student);
    }

    private Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("Student with id: '%s' does not exists", id)));
    }
}
