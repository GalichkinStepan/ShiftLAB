package ru.cft.ShiftLAB.errors;

import lombok.Value;

@Value
public class Error {

    int code;

    String message;
}
