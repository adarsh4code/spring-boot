package com.adarsh.springboot.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner runner(StudentRepository repository){
        return args -> {
            Student adarsh = new Student(

                    "adarsh",
                    "adcoolnrock@gmail.com",
                    LocalDate.of(1992, Month.DECEMBER, 25)

            );
            Student garima = new Student(

                    "garima",
                    "garima@gmail.com",
                    LocalDate.of(2019, Month.AUGUST, 18)

            );
            repository.saveAll(
                    List.of(adarsh, garima)
            );
        };
    }
}
