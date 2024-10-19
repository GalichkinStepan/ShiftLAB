package ru.cft.ShiftLAB.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.services.AnalyticsService;
import ru.cft.ShiftLAB.services.SellerService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AnalyticsContollerTest {

    @Mock
    AnalyticsService analyticsService;

    @InjectMocks
    AnalyticsController analyticsController;

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(analyticsController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getBestSeller() throws Exception{
        Seller seller = new Seller("Galichkin", "+79130084886");

        Mockito.when(analyticsService.getBestSeller(Mockito.any())).thenReturn(seller);

        mockMvc.perform(get("/analytics/bestseller"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Galichkin"))
                .andExpect(jsonPath("$.contactInfo").value("+79130084886"));

        Mockito.verify(analyticsService, Mockito.times(1)).getBestSeller(Mockito.any());
    }

    @Test
    void getLowSumSellers() throws Exception{
        Seller seller1 = new Seller("Galichkin", "+79130084886");
        Seller seller2 = new Seller("Stepan", "s.galichkin.work@gmail.com");

        Mockito.when(analyticsService.getLowSumSellers(Mockito.any(), Mockito.anyInt())).thenReturn(List.of(seller1, seller2));

        mockMvc.perform(get("/analytics/lowsumsellers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Galichkin"))
                .andExpect(jsonPath("$[1].contactInfo").value("s.galichkin.work@gmail.com"));

        Mockito.verify(analyticsService, Mockito.times(1)).getLowSumSellers(Mockito.any(), Mockito.anyInt());
    }
}
