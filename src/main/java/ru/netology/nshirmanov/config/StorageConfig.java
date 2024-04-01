package ru.netology.nshirmanov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.nshirmanov.entities.Customer;
import ru.netology.nshirmanov.entities.Operation;
import ru.netology.nshirmanov.services.StorageService;

@Configuration
public class StorageConfig {
    @Bean
    public StorageService<Customer> customerStorageService() {
        return new StorageService<>();
    }

    @Bean
    public StorageService<Operation> operationStorageService() {
        return new StorageService<>();
    }
}
