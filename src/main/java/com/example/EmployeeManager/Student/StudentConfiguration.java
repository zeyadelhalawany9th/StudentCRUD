package com.example.EmployeeManager.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration
{
    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository)
    {
        return args ->
        {
            Student Zeyad = new Student(
                    4L, "Zeyad", "zeyad@email.com", LocalDate.of(1998, Month.MAY, 30));

            Student Omar = new Student(
                    4L, "Omar", "omar@email.com", LocalDate.of(1998, Month.APRIL, 21));

            studentRepository.saveAll(List.of(Zeyad, Omar));
        };

    }

}
