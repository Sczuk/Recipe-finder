package br.com.recipe_finder.exception;

import org.springframework.http.HttpStatus;

public class DrinkNotFoundException extends RuntimeException implements ExceptionInterface{

    private final String code = "DRINK_NOT_FOUND";

    private final int httpStatus = HttpStatus.NOT_FOUND.value();

    public DrinkNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }
}
