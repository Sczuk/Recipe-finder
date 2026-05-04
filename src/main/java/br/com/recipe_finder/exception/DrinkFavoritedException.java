package br.com.recipe_finder.exception;

import org.springframework.http.HttpStatus;

public class DrinkFavoritedException extends RuntimeException implements ExceptionInterface{
    private final String code = "DRINK_FAVORITED";

    private final int httpStatus = HttpStatus.BAD_REQUEST.value();

    public DrinkFavoritedException(String message) {
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
