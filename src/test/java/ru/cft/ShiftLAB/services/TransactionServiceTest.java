package ru.cft.ShiftLAB.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cft.ShiftLAB.controllers.dto.TransactionCreateRequest;
import ru.cft.ShiftLAB.exceptions.CommonException;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.models.Transaction;
import ru.cft.ShiftLAB.repositories.SellerRepository;
import ru.cft.ShiftLAB.repositories.TransactionRepository;
import ru.cft.ShiftLAB.services.implementation.TransactionServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    SellerRepository sellerRepository;

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Test
    void getAllNotEmpty() {

        Transaction transaction1 = new Transaction(new Seller(
                "Galichkin", "+79130084886"),
                100,
                "CASH");
        Transaction transaction2 = new Transaction(
                new Seller("Stepan", "s.galichkin.work@gmail.com"),
                200,
                "CARD");
        Mockito.when(transactionRepository.findAll()).thenReturn(List.of(transaction1, transaction2));

        List<Transaction> allTransactions = transactionService.getAll();

        Assertions.assertEquals("Galichkin", allTransactions.get(0).getSeller().getName());

        Assertions.assertEquals(200, allTransactions.get(1).getAmount());
    }

    @Test
    void getAllEmpty() {
        List<Transaction> emptyListTransactions = new ArrayList<>();
        Mockito.when(transactionRepository.findAll()).thenReturn(emptyListTransactions);

        Assertions.assertThrows(CommonException.class, () -> transactionService.getAll());

    }

    @Test
    void getByIdFound(){
        Mockito.when(transactionRepository.findById(Mockito.anyLong()))
                .thenReturn(
                        Optional.of(new Transaction(new Seller(
                                "Galichkin", "+79130084886"),
                                100,
                                "CASH"))
                );

        Transaction gettingTransaction = transactionService.getById(1);

        Assertions.assertEquals(gettingTransaction.getSeller().getName(), "Galichkin");

        Assertions.assertEquals(gettingTransaction.getAmount(), 100);

    }

    @Test
    void getByIdNotFound(){
        Mockito.when(transactionRepository.findById(Mockito.anyLong()))
                .thenReturn(
                        Optional.empty()
                );
        Assertions.assertThrows(CommonException.class, () -> transactionService.getById(1));
    }

    @Test
    void create(){
        Seller seller = new Seller("Galichkin", "+79130084886");
        TransactionCreateRequest transactionCreateRequest =
                new TransactionCreateRequest(1, 100, "CASH");

        Transaction transaction = new Transaction(seller, 100, "CASH");

        Mockito.when(transactionRepository.save(Mockito.any()))
                .thenReturn(
                        transaction
                );

        Mockito.when(sellerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(seller));


        Transaction createdTransaction = transactionService.create(transactionCreateRequest);

        Assertions.assertEquals(createdTransaction.getSeller().getName(), "Galichkin");

        Assertions.assertEquals(createdTransaction.getAmount(), 100);
    }

    @Test
    void getAllForSellerFound(){
        Seller seller = new Seller("Galichkin", "+79130084886");

        Transaction transaction1 = new Transaction(seller, 100, "CASH");
        Transaction transaction2 = new Transaction(seller, 200, "CARD");

        Mockito.when(transactionRepository.getAllForSeller(Mockito.anyLong())).thenReturn(List.of(transaction1, transaction2));

        List<Transaction> allTransactions = transactionService.getAllForSeller(1);

        Assertions.assertEquals("Galichkin", allTransactions.get(0).getSeller().getName());

        Assertions.assertEquals(200, allTransactions.get(1).getAmount());
    }

    @Test
    void getAllForSellerNotFound(){

        List<Transaction> emptyListTransactions = new ArrayList<>();

        Mockito.when(transactionRepository.getAllForSeller(Mockito.anyLong())).thenReturn(emptyListTransactions);

        Assertions.assertThrows(CommonException.class, () -> transactionService.getAllForSeller(1));
    }
}
