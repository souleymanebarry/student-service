package com.barry.student;


import com.barry.student.models.Student;
import com.barry.student.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static com.barry.student.enums.Gender.FEMALE;
import static com.barry.student.enums.Gender.MALE;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner start(StudentRepository studentRepository){
        return args -> {
            List<Student> students =List.of(
                Student.builder()
                    .id(1L)
                    .name("John")
                    .email("john@gmail.com")
                    .gender(MALE)
                    .build(),

                    Student.builder()
                            .id(2L)
                            .name("Aisha")
                            .email("aisha@gmail.com")
                            .gender(FEMALE)
                            .build(),

                    Student.builder()
                            .id(3L)
                            .name("Souleymane")
                            .email("souleymane@gmail.com")
                            .gender(MALE)
                            .build(),

                    Student.builder()
                            .id(4L)
                            .name("Victor")
                            .email("victor@gmail.com")
                            .gender(MALE)
                            .build(),
                            Student.builder()
                                    .id(5L)
                                    .name("Aline")
                                    .email("aline@gmail.com")
                                    .gender(FEMALE)
                                    .build()
            );
            studentRepository.saveAll(students).forEach(System.out::println);


        };
    }
}
