package ru.netology.nshirmanov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.netology.nshirmanov.entities.CashbackOperation;
import ru.netology.nshirmanov.entities.LoanOperation;
import ru.netology.nshirmanov.entities.Operation;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {
    private int id;
    private double amount;
    private int customerId;
    private String description;
    private LocalDate date;
    private int cashbackAmount;
    private int loanId;

    public OperationDto(Operation operation) {
        this.id = operation.getId();
        this.amount = operation.getAmount();
        this.customerId = operation.getCustomerId();
        this.description = operation.getDescription();
        this.date = operation.getDate();
    }

    public OperationDto(CashbackOperation operation) {
        this.id = operation.getId();
        this.amount = operation.getAmount();
        this.customerId = operation.getCustomerId();
        this.description = operation.getDescription();
        this.date = operation.getDate();
        this.cashbackAmount = operation.getCashbackAmount();
    }

    public OperationDto(LoanOperation operation) {
        this.id = operation.getId();
        this.amount = operation.getAmount();
        this.customerId = operation.getCustomerId();
        this.description = operation.getDescription();
        this.date = operation.getDate();
        this.loanId = operation.getLoanId();
    }
}
