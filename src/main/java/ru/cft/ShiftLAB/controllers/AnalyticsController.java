package ru.cft.ShiftLAB.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.cft.ShiftLAB.controllers.dto.BestSellerRequest;
import ru.cft.ShiftLAB.controllers.dto.DurationRequest;
import ru.cft.ShiftLAB.models.Seller;
import ru.cft.ShiftLAB.services.AnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@Slf4j
@RequiredArgsConstructor
public class AnalyticsController {

     private final AnalyticsService analyticsService;

    // Получить самого продуктивного продавца
    @GetMapping("/bestseller")
    public Seller bestSeller(@RequestBody DurationRequest durationRequest){
        return analyticsService.getBestSeller(durationRequest);
    }

    // Получить список продавцов с суммой меньше указанной
    @GetMapping("/lowsumsellers")
    public List<Seller> lowSumSellers(@RequestBody DurationRequest durationRequest, @RequestParam int boundaryAmount){
        return analyticsService.getLowSumSellers(durationRequest, boundaryAmount);
    }

    // Получить самое продуктивное время продавца

}
