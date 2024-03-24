package ru.netology.nshirmanov.services;

import ru.netology.nshirmanov.Operation;

import java.util.LinkedList;
import java.util.Queue;

public class AsyncInputOperationService {
    private final Queue<Operation> queue = new LinkedList<>();
    private final OperationService operationService;
    private final lOService ioService;

    public AsyncInputOperationService(OperationService operationService, lOService lOService) {
        this.operationService = operationService;
        this.ioService = lOService;
    }


    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }

    public void startAsyncOperationProcessing() {
        Thread t = new Thread(this::processQueue);
        t.start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    ioService.printMessage("Ожидание следующей операции в очереди");
                    Thread.sleep(1_000);
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
