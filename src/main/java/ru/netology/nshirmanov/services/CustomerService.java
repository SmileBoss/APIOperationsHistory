package ru.netology.nshirmanov.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.nshirmanov.dto.CustomerDto;
import ru.netology.nshirmanov.entities.Customer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {
    private final StorageService<Customer> customers;

    @Autowired
    public CustomerService(StorageService<Customer> customerStorage) {
        this.customers = customerStorage;
    }

    @PostConstruct
    public void initStorage() {
        customers.addItem(new Customer(0, "Spring", "email"));
        customers.addItem(new Customer(1, "Boot", "email"));
    }

    public Customer createCustomer(String name, String email) {
        int newId = customers.size();
        Customer newCustomer = new Customer(newId, name, email);
        customers.addItem(newCustomer);
        return newCustomer;
    }

    public Customer getCustomer(int id) {
        return customers.getItem(id);
    }

    public void deleteCustomer(int id) {
        customers.removeItem(id);
    }

    public List<Customer> getAllCustomers() {
        return customers.getAllItems();
    }

    public void serialize(String path) throws IOException {
        customers.serialize(path);
    }

    public void deserialize(String path) throws IOException, ClassNotFoundException {
        customers.deserialize(path);
    }

    public Customer updateCustomer(CustomerDto dto) {
        Customer customer = customers.getItem(dto.getId());
        if (Objects.nonNull(dto.getName())) {
            customer.setName(dto.getName());
        }
        if (Objects.nonNull(dto.getEmail())) {
            customer.setEmail(dto.getEmail());
        }
        customers.updateItem(dto.getId(), customer);

        return customers.getItem(dto.getId());
    }
}
