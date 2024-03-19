package ru.netology.nshirmanov;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationData implements Serializable {
    private List<Operation> operations;
    private ArrayList<Customer> customers;
    private ArrayList<ArrayList<Integer>> statements;
}
