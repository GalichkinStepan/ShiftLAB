package ru.cft.ShiftLAB.controllers.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Jacksonized
@Builder
public record DurationRequest(LocalDateTime startTime, LocalDateTime endTime) {

}
