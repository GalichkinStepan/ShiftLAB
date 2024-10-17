package ru.cft.ShiftLAB.controllers.dto;

import java.time.LocalDateTime;

public record DurationRequest(LocalDateTime startTime, LocalDateTime endTime) {

}
