package az.ingress.oca46.MyFirstSpringProject.exception;

import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.AccountNotFound;
import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.AlreadyUserExistException;
import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.CustomerNotFound;
import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExpHandlerMap handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ExpHandlerMap.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorDescription(errors)
                .path(req.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExpHandler handleNotReadable(HttpMessageNotReadableException ex, HttpServletRequest req) {
        return buildExpHandler(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExpHandler handleAccountNotFound(AccountNotFound ex, HttpServletRequest req) {
        return buildExpHandler(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExpHandler handleCustomerNotFound(CustomerNotFound ex, HttpServletRequest req) {
        return buildExpHandler(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExpHandler handleUserNotFound(UserNotFoundException ex, HttpServletRequest req) {
        return buildExpHandler(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExpHandler handleAlreadyExist(AlreadyExistException ex, HttpServletRequest req) {
        return buildExpHandler(HttpStatus.CONFLICT, ex.getMessage(), req);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExpHandler handleAlreadyUserExist(AlreadyUserExistException ex, HttpServletRequest req) {
        return buildExpHandler(HttpStatus.CONFLICT, ex.getMessage(), req);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExpHandler handleNoSuchElement(NoSuchElementException ex, HttpServletRequest req) {
        return buildExpHandler(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExpHandler handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req) {
        return buildExpHandler(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExpHandler handleGeneral(Exception ex, HttpServletRequest req) {
        return buildExpHandler(HttpStatus.INTERNAL_SERVER_ERROR,
                "Gözlənilməz xəta: " + ex.getMessage(), req);
    }

    private ExpHandler buildExpHandler(HttpStatus status, String description, HttpServletRequest req) {
        return ExpHandler.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(status.value())
                .errorDescription(description)
                .path(req.getRequestURI())
                .status(status.getReasonPhrase())
                .build();
    }
}
