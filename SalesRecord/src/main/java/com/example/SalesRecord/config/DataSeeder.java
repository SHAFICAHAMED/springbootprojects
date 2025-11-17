package com.example.SalesRecord.config;


import com.example.SalesRecord.model.SalesRecordmodel;
import com.example.SalesRecord.repo.SalesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(SalesRepository repository) {
        return args -> {
            repository.saveAll(List.of(
                    new SalesRecordmodel(LocalDate.of(2024, 3, 1), "Laptop", 1500.0, 20),
                    new SalesRecordmodel(LocalDate.of(2024, 3, 2), "Smartphone", 1200.0, 30),
                    new SalesRecordmodel(LocalDate.of(2024, 3, 3), "Tablet", 800.0, 25)
            ));
        };
    }
}
