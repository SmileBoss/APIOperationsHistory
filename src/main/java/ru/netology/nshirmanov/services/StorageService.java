package ru.netology.nshirmanov.services;

import org.springframework.stereotype.Component;
import ru.netology.nshirmanov.exceptions.OperationRuntimeException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StorageService<T> implements Serializable {

    private final List<T> storage = new ArrayList<>();

    public void addItem(T item) {
        storage.add(item);
    }

    public T getItem(int index) {
        return storage.get(index);
    }

    public void updateItem(int index, T newItem) {
        try {
            storage.set(index, newItem);
        } catch (IndexOutOfBoundsException e) {
            throw new OperationRuntimeException("Index: {0}, Size: {1}", e, index, storage.size());
        }
    }

    public List<T> getAllItems() {
        return new ArrayList<>(storage);
    }

    public int size() {
        return storage.size();
    }

    public void serialize(String path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(storage);
        }
    }

    public void removeItem(int index) {
        storage.remove(index);
    }

    @SuppressWarnings("unchecked")
    public void deserialize(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            List<T> list = (List<T>) ois.readObject();
            storage.clear();
            storage.addAll(list);
        }
    }

}
