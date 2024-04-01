package ru.netology.nshirmanov.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.nshirmanov.dto.CustomerDto;
import ru.netology.nshirmanov.services.CustomerService;

import java.util.List;

@RestController
@Tag(name = "customers")
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(description = "Create")
    @PostMapping
    public CustomerDto create(@RequestBody CustomerDto dto) {
        return new CustomerDto(customerService.createCustomer(dto.getName(), dto.getEmail()));
    }

    @Operation(description = "Get all")
    @GetMapping
    public List<CustomerDto> getCustomers() {
        return customerService.getAllCustomers().stream()
                .map(CustomerDto::new)
                .toList();
    }

    @Operation(description = "Get by id")
    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable("id") int id) {
        return new CustomerDto(customerService.getCustomer(id));
    }

    @Operation(description = "Update")
    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@RequestBody CustomerDto dto) {
        return new CustomerDto(customerService.updateCustomer(dto));
    }

    @Operation(description = "Delete")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
    }
}
