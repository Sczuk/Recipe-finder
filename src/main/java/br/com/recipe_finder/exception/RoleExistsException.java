package br.com.recipe_finder.exception;

import org.springframework.http.HttpStatus;

public class RoleExistsException extends RuntimeException implements ExceptionInterface {

    private final String code = "ROLE_EXISTS";

    private final int httpStatus = HttpStatus.CONFLICT.value();


    public RoleExistsException(String message) {
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
