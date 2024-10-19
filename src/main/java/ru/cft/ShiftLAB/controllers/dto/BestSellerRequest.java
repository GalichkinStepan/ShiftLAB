package ru.cft.ShiftLAB.controllers.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record BestSellerRequest(long sellerId, int sum) {
}
