package ru.cft.ShiftLAB.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.TransactionCreateRequest;
import ru.cft.ShiftLAB.exceptions.NotFoundException;
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
        List<Transaction> transactions = transactionRepository.findAll();

        if(!transactions.isEmpty()) {
            return transactions;
        }
        else{
            throw new NotFoundException(404, "DATA_IS_EMPTY");
        }
    }

    // Получить информацию о конкретной транзакции
    @Override
    public Transaction getById(long id) {
        try{
            return  transactionRepository.findById(id).get();
        }
        catch (Exception ex) {
            throw new NotFoundException(404, "TRANSACTION_NOT_FOUND");
        }
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
        List<Transaction> transactions = transactionRepository.getAllForSeller(sellerId);
        if(!transactions.isEmpty()) {
            return transactions;
        }
        else {
            throw new NotFoundException(404, "DATA_IS_EMPTY");
        }
    }
}
