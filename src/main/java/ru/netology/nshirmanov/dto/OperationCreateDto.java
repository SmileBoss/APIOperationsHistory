package ru.netology.nshirmanov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationCreateDto {
    private int id;
    private double amount;
    private int customerId;
    private String description;
    private LocalDate date;
    private Integer additionalData;
}
