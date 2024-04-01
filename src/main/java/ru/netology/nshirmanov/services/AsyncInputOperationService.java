package ru.netology.nshirmanov.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.netology.nshirmanov.entities.Operation;
import ru.netology.nshirmanov.config.OperationProperties;

import java.util.LinkedList;
import java.util.Queue;

@Component
@RequiredArgsConstructor
public class AsyncInputOperationService {

    public static Queue<Operation> queue = new LinkedList<>();
    private final OperationProperties operationProperties;
    private final OperationService operationService;
    private final lOService ioService;

    @PostConstruct
    public void init() {
        this.startAsyncOperationProcessing();
    }

    public void startAsyncOperationProcessing() {
        Thread t = new Thread(this::processQueue);
        t.start();
    }

    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    ioService.printMessage("Ожидание следующей операции в очереди");
                    Thread.sleep(operationProperties.getSleepMilliseconds());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ioService.printMessage("Операция обработки: ");
                operation.print();
                operationService.addOperation(operation);
            }
        }
    }

}
