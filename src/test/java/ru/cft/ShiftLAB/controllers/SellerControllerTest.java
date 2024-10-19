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
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.services.SellerService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SellerControllerTest {

    @Mock
    SellerService sellerService;

    @InjectMocks
    SellerController sellerController;

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(sellerController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllSellers() throws Exception{
        Seller seller1 = new Seller("Galichkin", "+79130084886");
        Seller seller2 = new Seller("Stepan", "s.galichkin.work@gmail.com");

        Mockito.when(sellerService.getAll()).thenReturn(List.of(seller1, seller2));

        mockMvc.perform(get("/seller"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Galichkin"))
                .andExpect(jsonPath("$[1].contactInfo").value("s.galichkin.work@gmail.com"));

        Mockito.verify(sellerService, Mockito.times(1)).getAll();
    }

    @Test
    void getSeller() throws Exception{
        Seller seller = new Seller("Galichkin", "+79130084886");

        Mockito.when(sellerService.getById(Mockito.anyLong())).thenReturn(seller);

        mockMvc.perform(get("/seller/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Galichkin"))
                .andExpect(jsonPath("$.contactInfo").value("+79130084886"));

        Mockito.verify(sellerService, Mockito.times(1)).getById(Mockito.anyLong());
    }

    @Test
    void createSeller() throws Exception{
        SellerCreateRequest sellerCreateRequest =
                new SellerCreateRequest("Galichkin", "+79130084886");
        Seller seller = new Seller("Galichkin", "+79130084886");

        String sellerCreateRequestJson = objectMapper.writeValueAsString(sellerCreateRequest);

        Mockito.when(sellerService.create(Mockito.any())).thenReturn(seller);

        mockMvc.perform(
                post("/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sellerCreateRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Galichkin"))
                .andExpect(jsonPath("$.contactInfo").value("+79130084886"));

        Mockito.verify(sellerService, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    void updateSeller() throws Exception{
        SellerCreateRequest sellerCreateRequest =
                new SellerCreateRequest("Galichkin", "+79130084886");

        String sellerCreateRequestJson = objectMapper.writeValueAsString(sellerCreateRequest);

        mockMvc.perform(
                put("/seller/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sellerCreateRequestJson))
                .andExpect(status().isAccepted());
    }
}
