package com.barry.student.unit.service.impl;


import com.barry.student.dtos.StudentDto;
import com.barry.student.exceptions.BadRequestException;
import com.barry.student.exceptions.StudentNotFoundException;
import com.barry.student.mappers.StudentMapper;
import com.barry.student.mappers.StudentMapperImpl;
import com.barry.student.models.Student;
import com.barry.student.repositories.StudentRepository;
import com.barry.student.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.barry.student.enums.Gender.FEMALE;
import static com.barry.student.enums.Gender.MALE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Captor
    private ArgumentCaptor<Student> argumentCaptor;

    @Spy
    private StudentMapper studentMapper = new StudentMapperImpl();

    @Test
    void shouldGetAllStudents() {
        //given
        List<Student> students =List.of(
                Student.builder().id(1L).name("John").email("john@gmail.com").gender(MALE).build(),
                Student.builder().id(2L).name("Aisha").email("aisha@gmail.com").gender(FEMALE).build());

        //when
        given(studentRepository.findAll()).willReturn(students);
        final List<StudentDto> result = studentService.getAllStudents();

        //then
        assertAll(
                ()-> assertThat(result).isNotNull(),
                ()-> assertEquals("John",result.get(0).getName()),
                ()-> assertThat(result.get(1).getEmail()).isEqualTo("aisha@gmail.com")
        );
        verify(studentRepository,times(1)).findAll();
    }


    @Test
    void shouldAddANewStudentWhenStudentEmailDoesNotExist() {
        //given
        Student student = Student.builder()
                        .id(1L)
                        .name("John")
                        .email("john@gmail.com")
                        .gender(MALE)
                        .build();

        //when
        given(studentRepository.selectExistsEmail(student.getEmail())).willReturn(false);
        studentService.addStudent(studentMapper.studentToStudentDto(student));

        //then
        verify(studentRepository,times(1)).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(student);
    }

    @Test
    void shouldThrowBadRequestExceptionWhenStudentEmailExists() {
        //given
        Student student = Student.builder()
                .id(1L)
                .name("John")
                .email("john@gmail.com")
                .gender(MALE)
                .build();

        //when
        given(studentRepository.selectExistsEmail(student.getEmail())).willReturn(true);
        assertThatThrownBy(()->
                studentService.addStudent(studentMapper.studentToStudentDto(student)))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(String.format("Email: '%s' is taken", student.getEmail()));

        //then
        verify(studentRepository,never()).save(any());
    }


    @Test
    void shouldDeleteStudentByIdWhenStudentIdExists(){
        //given
        Long id = 1L;

        //when
        given(studentRepository.existsById(id)).willReturn(true);
        studentService.deleteStudent(id);

        //then
        verify(studentRepository).deleteById(id);
    }

    @Test
    void shouldThrowStudentNotFoundExceptionWhenStudentIdDoesNotExists(){
        //given
        Long id = 1L;

        //when
        given(studentRepository.existsById(id)).willReturn(false);
        assertThatThrownBy(()->studentService.deleteStudent(id))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining(String.format("Student with id: '%s' does not exists", id));
        //then
        verify(studentRepository,never()).deleteById(id);
    }

    @Test
    void shouldRetrieveStudentWhenStudentIdExists(){
        //given
        Student student = Student.builder()
                .id(1L)
                .name("John")
                .email("john@gmail.com")
                .gender(MALE)
                .build();

        //when
        given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));
        StudentDto result = studentService.getStudentById(student.getId());

        //then

        assertAll(
                ()-> assertThat(result).isNotNull(),
                ()-> assertThat(result.getId()).isEqualTo(1L)
        );

        verify(studentRepository,times(1)).findById(1L);
    }


    @Test
    void shouldThrowExceptionWhenStudentIdDoesNotExists(){
        //given
        Long id = 1L;

        //when
        given(studentRepository.findById(id)).willReturn(Optional.empty());
        assertThatThrownBy(()->
                studentService.getStudentById(id))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining(String.format("Student with id: '%s' does not exists", id));

        //then
        verify(studentRepository,times(1)).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenStudentIdDoesIsNull(){
        //given
        Long id = null;

        //when
        assertThatThrownBy(()->
                studentService.getStudentById(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid id: it should not be Empty");
        //then
        verify(studentRepository,never()).findById(anyLong());
    }

}
