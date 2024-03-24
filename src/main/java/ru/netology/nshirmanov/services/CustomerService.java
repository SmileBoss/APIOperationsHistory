package ru.netology.nshirmanov.services;

import lombok.Data;
import ru.netology.nshirmanov.Customer;

import java.io.IOException;
import java.util.List;

@Data
public class CustomerService {
    private final StorageService<Customer> customers;

    public CustomerService(StorageService<Customer> customers) {
        this.customers = customers;
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

    public List<Customer> getAllCustomers() {
        return customers.getAllItems();
    }

    public void serialize(String path) throws IOException {
        customers.serialize(path);
    }

    public void deserialize(String path) throws IOException, ClassNotFoundException {
        customers.deserialize(path);
    }
}
