package ru.netology.nshirmanov;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LoanOperation extends Operation {
    private int loanId;

    public LoanOperation(int id, double amount, int customerId, String description, LocalDate date, int loanId) {
        super(id, amount, customerId, description, date);
        this.loanId = loanId;
    }

    @Override
    public void print() {
        System.out.println("ID транзакции: " + getId() +
                ", Описание: " + getDescription() +
                ", Сумма: " + getAmount() +
                ", Дата: " + getDate() +
                ", ID кредита: " + loanId);
    }

}
