package ru.cft.ShiftLAB.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.SellerCreateRequest;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.repositories.SellerRepository;
import ru.cft.ShiftLAB.services.SellerService;

import java.util.List;

@Service(value = "sellerService")
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    // Список всех продавцов
    @Override
    public List<Seller> getAll(){

        List<Seller> sellers = sellerRepository.findAll();

        if(!sellers.isEmpty()) {
            return sellers;
        }
        else{
            // TODO: Кинуть ексепшен пустая БД
            return null;
        }

    }

    // Инфо о конкретном продавце
    @Override
    public Seller getById(long id){
        return sellerRepository.findById(id).get();
        // TODO: Кинуть исключение пользователь не найден
    }

    // Создать нового продавца
    @Override
    public Seller create(SellerCreateRequest sellerInfo){
        Seller newSeller = new Seller(sellerInfo.name(), sellerInfo.contactInfo());
        sellerRepository.save(newSeller);
        return newSeller;
        //TODO: Продумать исключения
    }

    // Обновить инфо о продавце
    @Override
    public void update(long id, SellerCreateRequest sellerInfo){
        Seller seller = sellerRepository.findById(id).get();
        seller.update(sellerInfo.name(), sellerInfo.contactInfo());
        sellerRepository.save(seller);
    }

    // Удалить продавца
    @Override
    public void delete(long id){
        sellerRepository.deleteById(id);
    }
}
