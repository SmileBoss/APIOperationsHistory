package ru.netology.nshirmanov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    private int clientId;
    private String name;
    private String email;
}
