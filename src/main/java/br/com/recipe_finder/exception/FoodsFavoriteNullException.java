package br.com.recipe_finder.exception;

import org.springframework.http.HttpStatus;

public class FoodsFavoriteNullException extends RuntimeException implements ExceptionInterface{
    private final String code = "FOOD_FAVORITES_NULL";

    private final int httpStatus = HttpStatus.NOT_FOUND.value();

    public FoodsFavoriteNullException(String message) {
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
