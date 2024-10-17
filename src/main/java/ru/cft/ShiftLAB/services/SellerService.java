package ru.cft.ShiftLAB.services;

import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.SellerCreateRequest;
import ru.cft.ShiftLAB.models.Seller;

import java.util.List;


public interface SellerService {

    // Список всех продавцов
    public List<Seller> getAll();

    // Инфо о конкретном продавце
    public Seller getById(long id);

    // Создать нового продавца
    public Seller create(SellerCreateRequest sellerInfo);

    // Обновить инфо о продавце
    public void update(long id, SellerCreateRequest sellerInfo);

    // Удалить продавца
    public void delete(long id);
}
