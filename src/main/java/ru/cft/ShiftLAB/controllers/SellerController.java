package ru.cft.ShiftLAB.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.ShiftLAB.controllers.dto.SellerCreateRequest;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.services.SellerService;

import java.util.List;

@RestController
@RequestMapping("/seller")
@Slf4j
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    // Список всех продавцов
    @GetMapping
    public List<Seller> getAllSellers(){
        return sellerService.getAll();
    }

    // Инфо о конкретном продавце
    @GetMapping("/{id}")
    public Seller getSeller(@PathVariable("id") long id){
        return sellerService.getById(id);
    }

    // Создать нового продавца
    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody SellerCreateRequest sellerCreateRequest) {
        Seller seller = sellerService.create(sellerCreateRequest);
        return new ResponseEntity<>(seller, HttpStatus.CREATED);
    }

    // Обновить инфо о продавце
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSeller(@PathVariable Long id, @RequestBody SellerCreateRequest sellerCreateRequest){
        sellerService.update(id, sellerCreateRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // Удалить продавца
    @DeleteMapping("/{id}")
    public void deleteSeller(@PathVariable("id") long id) {
        sellerService.delete(id);
    }
}
