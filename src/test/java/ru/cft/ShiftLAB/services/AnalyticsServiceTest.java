package ru.cft.ShiftLAB.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cft.ShiftLAB.controllers.dto.DurationRequest;
import ru.cft.ShiftLAB.exceptions.NotFoundException;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.repositories.SellerRepository;
import ru.cft.ShiftLAB.services.implementation.AnalyticsServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AnalyticsServiceTest {

    @Mock
    SellerRepository sellerRepository;

    @InjectMocks
    AnalyticsServiceImpl analyticsService;

    @Test
    void getBestSellerFound(){
        DurationRequest durationRequest =
                new DurationRequest(
                        LocalDateTime.parse("2024-10-16T18:25:48"),
                        LocalDateTime.parse("2024-10-18T18:27:43"));
        Seller seller = new Seller("Galichkin", "+79130084886");

        Mockito.when(sellerRepository.bestSeller(Mockito.any(), Mockito.any())).thenReturn(seller);

        analyticsService.getBestSeller(durationRequest);

        Assertions.assertEquals(seller.getName(), "Galichkin");

    }

    @Test
    void getBestSellerNotFound(){
        DurationRequest durationRequest =
                new DurationRequest(
                        LocalDateTime.parse("2024-10-16T18:25:48"),
                        LocalDateTime.parse("2024-10-18T18:27:43"));
        Mockito.when(sellerRepository.bestSeller(Mockito.any(), Mockito.any())).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> analyticsService.getBestSeller(durationRequest));
    }

    @Test
    void getLowSumSellersNotEmpty(){
        DurationRequest durationRequest =
            new DurationRequest(
                    LocalDateTime.parse("2024-10-16T18:25:48"),
                    LocalDateTime.parse("2024-10-18T18:27:43"));
        Seller seller1 = new Seller("Galichkin", "+79130084886");
        Seller seller2 = new Seller("Stepan", "s.galichkin.work@gmail.com");

        Mockito.when(sellerRepository.lowSumSellers(Mockito.any(), Mockito.any(), Mockito.anyInt())).thenReturn(List.of(seller1, seller2));

        List<Seller> lowSumSellers = analyticsService.getLowSumSellers(durationRequest, 100);

        Assertions.assertEquals(seller1.getName(), lowSumSellers.get(0).getName());

        Assertions.assertEquals(seller2.getContactInfo(), lowSumSellers.get(1).getContactInfo());
    }

    @Test
    void getLowSumSellersEmpty(){
        DurationRequest durationRequest =
                new DurationRequest(
                        LocalDateTime.parse("2024-10-16T18:25:48"),
                        LocalDateTime.parse("2024-10-18T18:27:43"));
        List<Seller> emptyListSellers = new ArrayList<>();
        Mockito.when(sellerRepository.lowSumSellers(Mockito.any(), Mockito.any(), Mockito.anyInt())).thenReturn(emptyListSellers);

        Assertions.assertThrows(NotFoundException.class, () -> analyticsService.getLowSumSellers(durationRequest, 100));
    }

}
