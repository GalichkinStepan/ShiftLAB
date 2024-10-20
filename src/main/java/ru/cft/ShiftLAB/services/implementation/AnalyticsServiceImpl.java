package ru.cft.ShiftLAB.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.DurationRequest;
import ru.cft.ShiftLAB.exceptions.NotFoundException;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.repositories.SellerRepository;
import ru.cft.ShiftLAB.services.AnalyticsService;

import java.util.List;

@Service(value = "analyticsService")
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final SellerRepository sellerRepository;

    // Получить самого продуктивного продавца
    @Override
    public Seller getBestSeller(DurationRequest durationRequest) {
        Seller bestSeller = sellerRepository.bestSeller(durationRequest.startTime(), durationRequest.endTime());
        if(bestSeller != null) {
            return bestSeller;
        }
        else{
            throw new NotFoundException(404, "SELLER_NOT_FOUND");
        }
    }


    @Override
    public List<Seller> getLowSumSellers(DurationRequest durationRequest, int boundaryAmount) {
        List<Seller> lowSumSellers = sellerRepository.lowSumSellers(durationRequest.startTime(), durationRequest.endTime(), boundaryAmount);
        if(!lowSumSellers.isEmpty()) {
            return lowSumSellers;
        }
        else {
            throw new NotFoundException(404, "SELLERS_NOT_FOUND");
        }
    }
}
