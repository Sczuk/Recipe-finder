package br.com.recipe_finder.exception;

import org.springframework.http.HttpStatus;

public class FoodFavoritedException extends RuntimeException implements ExceptionInterface{
    private final String code = "FOOD_FAVORITED";

    private final int httpStatus = HttpStatus.BAD_REQUEST.value();

    public FoodFavoritedException(String message) {
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
