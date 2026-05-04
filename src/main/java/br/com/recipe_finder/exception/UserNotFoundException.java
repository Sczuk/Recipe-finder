package br.com.recipe_finder.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException implements ExceptionInterface{

    private final String code = "USER_NOT_FOUND";
    private int httpStatus = HttpStatus.NOT_FOUND.value();

    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public int getHttpStatus() {
        return this.httpStatus;
    }
}
