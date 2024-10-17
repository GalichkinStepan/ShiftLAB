package ru.cft.ShiftLAB.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.TransactionCreateInfo;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.models.Transaction;
import ru.cft.ShiftLAB.repositories.TransactionRepository;
import ru.cft.ShiftLAB.services.TransactionService;

import java.util.List;

@Service(value = "transactionService")
public class TransactionServiceImpl  implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    // Получить список всех транзакций
    @Override
    public List<Seller> getAll(){
        return null;
    }

    // Получить информацию о конкретной транзакции
    @Override
    public Transaction getById(int id) {
        return null;
    }

    // Создать новую транзакцию
    @Override
    public void create(TransactionCreateInfo transactionInfo) {

    }

    // Получить все транзакции продавца
    @Override
    public List<Transaction> getAllForSeller(int sellerId){
        return null;
    }
}
