package ru.netology.nshirmanov;

import ru.netology.nshirmanov.exceptions.OperationRuntimeException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OperationDataIO {

    public static void serializeData(OperationData data) {
        String filename = "src/main/resources/export.txt";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(data);
        } catch (IOException e) {
            throw new OperationRuntimeException("Ошибка сериализации данных в файл {0} ", e, filename);
        }
    }

    public static OperationData deserializeData(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (OperationData) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new OperationRuntimeException("Ошибка десериализации файла {0} ", e, filename);
        }
    }
}
