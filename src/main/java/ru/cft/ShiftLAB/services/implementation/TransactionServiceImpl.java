package ru.cft.ShiftLAB.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.TransactionCreateRequest;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.models.Transaction;
import ru.cft.ShiftLAB.repositories.SellerRepository;
import ru.cft.ShiftLAB.repositories.TransactionRepository;
import ru.cft.ShiftLAB.services.TransactionService;

import java.util.List;

@Service(value = "transactionService")
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final SellerRepository sellerRepository;

    // Получить список всех транзакций
    @Override
    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }

    // Получить информацию о конкретной транзакции
    @Override
    public Transaction getById(long id) {
        return  transactionRepository.findById(id).get();
    }

    // Создать новую транзакцию
    @Override
    public Transaction create(TransactionCreateRequest transactionInfo) {
        Seller seller = sellerRepository.findById(transactionInfo.sellerId()).get();
        Transaction newTransaction = new Transaction(
                seller,
                transactionInfo.amount(),
                transactionInfo.paymentType()
        );
        transactionRepository.save(newTransaction);
        return newTransaction;
    }

    // Получить все транзакции продавца
    @Override
    public List<Transaction> getAllForSeller(long sellerId){
        return transactionRepository.getAllForSeller(sellerId);
    }
}
