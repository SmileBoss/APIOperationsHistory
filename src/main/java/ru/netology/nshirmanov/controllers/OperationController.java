package ru.netology.nshirmanov.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.nshirmanov.dto.OperationCreateDto;
import ru.netology.nshirmanov.dto.OperationDto;
import ru.netology.nshirmanov.entities.Operation;
import ru.netology.nshirmanov.enums.OperationType;
import ru.netology.nshirmanov.services.OperationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@Tag(name = "operations")
@RequiredArgsConstructor
@RequestMapping("/api/operations")
public class OperationController {

    private final OperationService operationService;

    @io.swagger.v3.oas.annotations.Operation(description = "Create")
    @PostMapping
    public OperationDto create(@RequestBody OperationCreateDto dto,
                               @RequestParam("type") OperationType type) {
        Operation operation = operationService.create(dto, type);
        return new OperationDto(operation);
    }

    @GetMapping("/{id}")
    public OperationDto getOperation(@PathVariable("id") int id) {
        return new OperationDto(operationService.getOperation(id));
    }

    @GetMapping("/customer/{id}")
    public List<OperationDto> getOperationsByCustomer(@PathVariable("id") int id,
                                                      @RequestParam(value = "dateFrom",
                                                              required = false) LocalDate dateFrom,
                                                      @RequestParam(value = "dateTo",
                                                              required = false) LocalDate dateTo) {
        List<Operation> operations = Objects.nonNull(dateFrom) && Objects.nonNull(dateTo)
                ? operationService.getOperationsByCustomerAndPeriod(id, dateFrom, dateTo)
                : operationService.getOperationsByCustomer(id);
        return operations.stream().map(OperationDto::new).toList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        operationService.deleteOperation(id);
    }
}
