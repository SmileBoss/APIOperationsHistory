package ru.netology.nshirmanov;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operation implements ConsolePrintable, Serializable {

    private int id;
    private double amount;
    private int customerId;
    private String description;
    private LocalDate date;

    @Override
    public void print() {
        System.out.println("ID транзакции: " + id +
                ", Описание: " + description +
                ", Сумма: " + amount +
                ", Дата: " + date);
    }
}
