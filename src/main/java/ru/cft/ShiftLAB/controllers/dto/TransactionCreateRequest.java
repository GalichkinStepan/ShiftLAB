package ru.cft.ShiftLAB.controllers.dto;

import lombok.Builder;
import ru.cft.ShiftLAB.models.Seller;

@Builder
public record TransactionCreateRequest (long sellerId, int amount, String paymentType){

}
