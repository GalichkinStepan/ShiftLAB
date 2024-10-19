package ru.cft.ShiftLAB.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.ShiftLAB.controllers.dto.SellerCreateRequest;
import ru.cft.ShiftLAB.controllers.dto.TransactionCreateRequest;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.models.Transaction;
import ru.cft.ShiftLAB.repositories.TransactionRepository;
import ru.cft.ShiftLAB.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Slf4j
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // Получить список всех транзакций
    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getAll();
    }

    // Получить информацию о конкретной транзакции
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable("id") long id){
        return transactionService.getById(id);
    }

    // Создать новую транзакцию
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionCreateRequest transactionCreateRequest) {
        return new ResponseEntity<>(transactionService.create(transactionCreateRequest), HttpStatus.ACCEPTED);
    }

    // Получить все транзакции продавца
    @GetMapping("/forSeller/{id}")
    public List<Transaction> getAllTransactionsForSeller(@PathVariable("id") long id){
        return transactionService.getAllForSeller(id);
    }
}
