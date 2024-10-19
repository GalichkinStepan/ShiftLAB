package ru.cft.ShiftLAB.controllers.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record SellerCreateRequest (String name, String contactInfo) {

}
