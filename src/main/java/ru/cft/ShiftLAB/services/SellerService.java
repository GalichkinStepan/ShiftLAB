package ru.cft.ShiftLAB.services;

import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.SellerCreateInfo;
import ru.cft.ShiftLAB.models.Seller;

import java.util.List;

@Service
public interface SellerService {

    // Список всех продавцов
    public List<Seller> getAll();

    // Инфо о конкретном продавце
    public Seller getById(int id);

    // Создать нового продавца
    public void create(SellerCreateInfo sellerInfo);

    // Обновить инфо о продавце
    public void update(int id);

    // Удалить продавца
    public void delete(int id);
}
