package ru.cft.ShiftLAB.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

    private final int errorCode;

    public NotFoundException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
