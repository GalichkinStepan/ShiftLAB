package ru.cft.ShiftLAB.controllers.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import ru.cft.ShiftLAB.models.Seller;

@Builder
@Jacksonized
public record TransactionCreateRequest (long sellerId, int amount, String paymentType){

}
