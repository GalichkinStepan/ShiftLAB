package ru.cft.ShiftLAB.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnalyticsService {

    // Получить самого продуктивного продавца
    public int getBestSeller();

    // Получить список продавцов с суммой меньше указанной
    public List<Integer> getLowSumSellers(int boundaryAmount);

    // Получить самое продуктивное время продавца
    public void getBestTime();

}
