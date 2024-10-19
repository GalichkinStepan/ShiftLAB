package ru.cft.ShiftLAB.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.cft.ShiftLAB.controllers.dto.SellerCreateRequest;
import ru.cft.ShiftLAB.controllers.dto.TransactionCreateRequest;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.models.Transaction;
import ru.cft.ShiftLAB.services.SellerService;
import ru.cft.ShiftLAB.services.TransactionService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    TransactionService transactionService;

    @InjectMocks
    TransactionController transactionController;

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
        objectMapper = new ObjectMapper();
    }


    @Test
    void getAllTransactions() throws Exception{
        Transaction transaction1 = new Transaction(
                new Seller("Galichkin", "+79130084886"),
                100,
                "CASH");
        Transaction transaction2 = new Transaction(
                new Seller("Stepan", "s.galichkin.work@gmail.com"),
                200,
                "CARD");
        Mockito.when(transactionService.getAll()).thenReturn(List.of(transaction1, transaction2));

        mockMvc.perform(get("/transaction"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].seller.name").value("Galichkin"))
                .andExpect(jsonPath("$[1].amount").value(200));

        Mockito.verify(transactionService, Mockito.times(1)).getAll();
    }

    @Test
    void getTransaction() throws Exception{
        Transaction transaction = new Transaction(
                new Seller("Galichkin", "+79130084886"),
                100,
                "CASH");

        Mockito.when(transactionService.getById(Mockito.anyLong())).thenReturn(transaction);

        mockMvc.perform(get("/transaction/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seller.name").value("Galichkin"))
                .andExpect(jsonPath("$.amount").value(100));

        Mockito.verify(transactionService, Mockito.times(1)).getById(Mockito.anyLong());
    }

    @Test
    void createTransaction() throws Exception{
        Seller seller = new Seller("Galichkin", "+79130084886");
        TransactionCreateRequest transactionCreateRequest =
                new TransactionCreateRequest(1, 100, "CASH");

        Transaction transaction = new Transaction(seller, 100, "CASH");

        String transactionCreateRequestJson = objectMapper.writeValueAsString(transactionCreateRequest);

        Mockito.when(transactionService.create(Mockito.any())).thenReturn(transaction);

        mockMvc.perform(
                        post("/transaction")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(transactionCreateRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.seller.name").value("Galichkin"))
                .andExpect(jsonPath("$.amount").value(100));

        Mockito.verify(transactionService, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    void getAllForSeller() throws Exception{
        Seller seller = new Seller("Galichkin", "+79130084886");

        Transaction transaction1 = new Transaction(
                seller,
                100,
                "CASH");
        Transaction transaction2 = new Transaction(
                seller,
                200,
                "CARD");
        Mockito.when(transactionService.getAllForSeller(Mockito.anyLong()))
                .thenReturn(List.of(transaction1, transaction2));

        mockMvc.perform(get("/transaction/forSeller/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].seller.name").value("Galichkin"))
                .andExpect(jsonPath("$[1].amount").value(200));

        Mockito.verify(transactionService, Mockito.times(1)).getAllForSeller(Mockito.anyLong());

    }
}
