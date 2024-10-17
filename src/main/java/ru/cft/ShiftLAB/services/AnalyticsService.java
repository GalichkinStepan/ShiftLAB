package ru.cft.ShiftLAB.services;

import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.controllers.dto.BestSellerRequest;
import ru.cft.ShiftLAB.controllers.dto.DurationRequest;
import ru.cft.ShiftLAB.models.Seller;

import java.util.List;


public interface AnalyticsService {

    // Получить самого продуктивного продавца
    public Seller getBestSeller(DurationRequest durationRequest);

    // Получить список продавцов с суммой меньше указанной
    public List<Seller> getLowSumSellers(DurationRequest durationRequest, int boundaryAmount);

    // Получить самое продуктивное время продавца
    public void getBestTime();


}
