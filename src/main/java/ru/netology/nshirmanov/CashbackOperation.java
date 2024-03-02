package ru.netology.nshirmanov;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CashbackOperation extends Operation {
    private int cashbackAmount;

    public CashbackOperation(int id, double amount, String description, LocalDate date, int cashbackAmount) {
        super(id, amount, description, date);
        this.cashbackAmount = cashbackAmount;
    }

    @Override
    public void print() {
        System.out.println("ID транзакции: " + getId() +
                ", Описание: " + getDescription() +
                ", Сумма: " + getAmount() +
                ", Дата: " + getDate() +
                ", Сумма кэшбэка: " + cashbackAmount);
    }
}
