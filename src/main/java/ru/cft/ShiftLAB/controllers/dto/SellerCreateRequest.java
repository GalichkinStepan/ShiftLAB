package ru.cft.ShiftLAB.controllers.dto;

import lombok.Builder;

@Builder
public record SellerCreateRequest (String name, String contactInfo) {

}
