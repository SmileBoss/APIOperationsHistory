package ru.netology.nshirmanov;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operation implements ConsolePrintable {

    private int id;
    private double amount;
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