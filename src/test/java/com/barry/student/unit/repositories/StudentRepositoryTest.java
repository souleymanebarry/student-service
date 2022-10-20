package com.barry.student.unit.repositories;

import com.barry.student.models.Student;
import com.barry.student.repositories.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.barry.student.enums.Gender.FEMALE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class StudentRepositoryTest {


    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void shouldCheckStudentEmailExists() {
        //given
        Student student = Student.builder()
                .id(2L)
                .name("Jamila")
                .email("Jamila@gmail.com")
                .gender(FEMALE)
                .build();
        studentRepository.save(student);

        //when
        boolean expected = studentRepository.selectExistsEmail(student.getEmail());

        //then
       assertThat(expected).isTrue();
    }

    @Test
    void shouldCheckStudentEmailDoesNotExists() {
        //given
        Student student = Student.builder()
                .id(2L)
                .name("Jamila")
                .email("Jamila@gmail.com")
                .gender(FEMALE)
                .build();

        //when
        boolean expected = studentRepository.selectExistsEmail(student.getEmail());

        //then
        assertThat(expected).isFalse();
    }
}