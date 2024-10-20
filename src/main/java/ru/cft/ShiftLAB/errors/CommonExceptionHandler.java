package ru.cft.ShiftLAB.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.cft.ShiftLAB.exceptions.NotFoundException;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handle(Exception exception) {
        log.error("Failed to process request", exception);

        return ResponseEntity.status(getHttpStatus(exception))
                .body(new Error(getErrorCode(exception), exception.getMessage()));
    }

    private static HttpStatus getHttpStatus(Exception exception) {
        if(exception instanceof NotFoundException) {
            if(((NotFoundException) exception).getErrorCode() == 404) {
                return HttpStatus.NOT_FOUND;
            }
        }
        if (exception instanceof NoHandlerFoundException) {
            return HttpStatus.NOT_FOUND;
        }
        if (exception instanceof HttpRequestMethodNotSupportedException) {
            return HttpStatus.METHOD_NOT_ALLOWED;
        }
        if (exception instanceof HttpMediaTypeNotAcceptableException) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
        if (exception instanceof HttpMediaTypeNotSupportedException) {
            return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        }
        if (exception instanceof HttpMessageNotReadableException) {
            return HttpStatus.BAD_REQUEST;
        }
        if (exception instanceof ServletRequestBindingException) {
            return HttpStatus.BAD_REQUEST;
        }
        if (exception instanceof MethodArgumentTypeMismatchException) {
            return HttpStatus.BAD_REQUEST;
        }
        if (exception instanceof NotFoundException notFoundException) {
            return getHttpStatus(notFoundException);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private static HttpStatus getHttpStatus(NotFoundException exception) {
        return switch (exception.getErrorCode()) {
            case 1 -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.BAD_REQUEST;
        };
    }

    private static int getErrorCode(Exception exception) {
        if (exception instanceof NotFoundException notFoundException) {
            return notFoundException.getErrorCode();
        }
        return 500;
    }
}
