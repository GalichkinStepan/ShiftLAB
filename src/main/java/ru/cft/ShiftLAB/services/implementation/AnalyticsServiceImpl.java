package ru.cft.ShiftLAB.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.BestSellerRequest;
import ru.cft.ShiftLAB.controllers.dto.DurationRequest;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.repositories.SellerRepository;
import ru.cft.ShiftLAB.repositories.TransactionRepository;
import ru.cft.ShiftLAB.services.AnalyticsService;

import java.util.List;

@Service(value = "analyticsService")
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final SellerRepository sellerRepository;

    // Получить самого продуктивного продавца
    @Override
    public Seller getBestSeller(DurationRequest durationRequest)
    {
        return sellerRepository.bestSeller(durationRequest.startTime(), durationRequest.endTime());
    }


    @Override
    public List<Seller> getLowSumSellers(DurationRequest durationRequest, int boundaryAmount)
    {
        return sellerRepository.lowSumSellers(durationRequest.startTime(), durationRequest.endTime(), boundaryAmount);
    }

    public void getBestTime()
    {

    }

}
