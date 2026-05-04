package br.com.recipe_finder.exception;

public interface ExceptionInterface{

    String getCode();
    String getMessage();
    int getHttpStatus();
}
