package ru.cft.ShiftLAB.services;

import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.TransactionCreateInfo;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.models.Transaction;

import java.util.List;

@Service
public interface TransactionService {

    // Получить список всех транзакций
    public List<Seller> getAll();

    // Получить информацию о конкретной транзакции
    public Transaction getById(int id);

    // Создать новую транзакцию
    public void create(TransactionCreateInfo transactionInfo);

    // Получить все транзакции продавца
    public List<Transaction> getAllForSeller(int sellerId);


}
