package ru.netology.nshirmanov.services;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class lOService {

    public lOService() {
    }

    public void printMessage(String message) {
        System.out.println(message);
    }


}
