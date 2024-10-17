package ru.cft.ShiftLAB.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.TransactionCreateRequest;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.models.Transaction;

import java.util.List;


public interface TransactionService {

    // Получить список всех транзакций
    public List<Transaction> getAll();

    // Получить информацию о конкретной транзакции
    public Transaction getById(long id);

    // Создать новую транзакцию
    public Transaction create(TransactionCreateRequest transactionInfo);

    // Получить все транзакции продавца
    public List<Transaction> getAllForSeller(long sellerId);

}
