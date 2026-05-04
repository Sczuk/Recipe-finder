package br.com.recipe_finder.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse {

    private String code;

    private int httpStatusCode;

    private String message;

}
