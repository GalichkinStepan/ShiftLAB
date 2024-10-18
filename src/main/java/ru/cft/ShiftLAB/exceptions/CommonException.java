package ru.cft.ShiftLAB.exceptions;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{

    private final int errorCode;

    public CommonException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
