package ru.cft.ShiftLAB.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Seller createSeller(@RequestBody SellerCreateRequest sellerCreateRequest) {
        return sellerService.create(sellerCreateRequest);
    }
    // Обновить инфо о продавце
    @PutMapping
    public void updateSeller(@RequestParam Long id, @RequestBody SellerCreateRequest sellerCreateRequest){
        sellerService.update(id, sellerCreateRequest);
    }
    // Удалить продавца
    @DeleteMapping("/{id}")
    public void deleteSeller(@PathVariable("id") long id) {
        sellerService.delete(id);
    }
}
