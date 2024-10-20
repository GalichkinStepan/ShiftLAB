package ru.cft.ShiftLAB.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cft.ShiftLAB.controllers.dto.SellerCreateRequest;
import ru.cft.ShiftLAB.exceptions.NotFoundException;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.repositories.SellerRepository;
import ru.cft.ShiftLAB.services.implementation.SellerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @Mock
    SellerRepository sellerRepository;

    @InjectMocks
    SellerServiceImpl sellerService;

    @Test
    void getAllNotEmpty() {
        Seller seller1 = new Seller("Galichkin", "+79130084886");
        Seller seller2 = new Seller("Stepan", "s.galichkin.work@gmail.com");

        Mockito.when(sellerRepository.findAll()).thenReturn(List.of(seller1, seller2));

        List<Seller> allSellers = sellerService.getAll();

        Assertions.assertEquals(seller1.getName(), allSellers.get(0).getName());

        Assertions.assertEquals(seller2.getContactInfo(), allSellers.get(1).getContactInfo());
    }

    @Test
    void getAllEmpty() {
        List<Seller> emptyListSellers = new ArrayList<>();
        Mockito.when(sellerRepository.findAll()).thenReturn(emptyListSellers);

        Assertions.assertThrows(NotFoundException.class, () -> sellerService.getAll());
    }

    @Test
    void getByIdFound(){

        Mockito.when(sellerRepository.findById(Mockito.anyLong()))
                .thenReturn(
                        Optional.of(new Seller("Galichkin", "+79130084886"))
                );

        Seller gettingSeller = sellerService.getById(1);

        Assertions.assertEquals(gettingSeller.getName(), "Galichkin");

        Assertions.assertEquals(gettingSeller.getContactInfo(), "+79130084886");

    }

    @Test
    void getByIdNotFound(){
        Mockito.when(sellerRepository.findById(Mockito.anyLong()))
                .thenReturn(
                        Optional.empty()
                );
        Assertions.assertThrows(NotFoundException.class, () -> sellerService.getById(1));
    }

    @Test
    void create(){
        SellerCreateRequest sellerCreateRequest =
                new SellerCreateRequest("Galichkin", "+79130084886");
        Seller seller = new Seller("Galichkin", "+79130084886");

        Mockito.when(sellerRepository.save(Mockito.any()))
                .thenReturn(
                        seller
                );

        Seller createdSeller = sellerService.create(sellerCreateRequest);

        Assertions.assertEquals(createdSeller.getName(), "Galichkin");

        Assertions.assertEquals(createdSeller.getContactInfo(), "+79130084886");
    }

    @Test
    void updateNotFound(){
        SellerCreateRequest sellerCreateRequest =
                new SellerCreateRequest("Galichkin", "+79130084886");
        Mockito.when(sellerRepository.findById(Mockito.anyLong()))
                .thenReturn(
                        Optional.empty()
                );

        Assertions.assertThrows(NotFoundException.class, () -> sellerService.update(1, sellerCreateRequest));

    }

    @Test
    void deleteNotFound(){
        Mockito.when(sellerRepository.findById(Mockito.anyLong()))
                .thenReturn(
                        Optional.empty()
                );
        Assertions.assertThrows(NotFoundException.class, () -> sellerService.getById((long)1));
    }

}
