package ru.netology.nshirmanov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.netology.nshirmanov.entities.Customer;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Integer id;
    private String name;
    private String email;

    public CustomerDto(Customer customer) {
        this.id = customer.getClientId();
        this.name = Optional.ofNullable(customer.getName()).orElse("");
        this.email = Optional.ofNullable(customer.getEmail()).orElse("");
    }
}
