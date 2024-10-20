package ru.cft.ShiftLAB.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.SellerCreateRequest;
import ru.cft.ShiftLAB.exceptions.NotFoundException;
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
            throw new NotFoundException(404, "DATA_IS_EMPTY");
        }

    }

    // Инфо о конкретном продавце
    @Override
    public Seller getById(long id){
        try {
            Seller seller = sellerRepository.findById(id).get();
            return seller;
        }
        catch (Exception ex) {
            throw new NotFoundException(404, "SELLER_NOT_FOUND");
        }

    }

    // Создать нового продавца
    @Override
    public Seller create(SellerCreateRequest sellerInfo){
            Seller newSeller = new Seller(sellerInfo.name(), sellerInfo.contactInfo());
            sellerRepository.save(newSeller);
            return newSeller;
    }

    // Обновить инфо о продавце
    @Override
    public void update(long id, SellerCreateRequest sellerInfo){
        try {
            Seller seller = sellerRepository.findById(id).get();
            seller.update(sellerInfo.name(), sellerInfo.contactInfo());
            sellerRepository.save(seller);
        }
        catch (Exception ex) {
            throw new NotFoundException(404, "SELLER_NOT_FOUND");
        }
    }

    // Удалить продавца
    @Override
    public void delete(long id){
        try {
            Seller seller = sellerRepository.findById(id).get();
        }
        catch (Exception ex) {
            throw new NotFoundException(404, "SELLER_NOT_FOUND");
        }
        sellerRepository.deleteById(id);
    }
}
