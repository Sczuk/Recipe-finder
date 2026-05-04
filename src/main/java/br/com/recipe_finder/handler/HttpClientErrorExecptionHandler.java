package br.com.recipe_finder.handler;

import br.com.recipe_finder.DTO.response.ExceptionResponse;
import br.com.recipe_finder.DTO.response.ValidateErrorResponse;
import br.com.recipe_finder.exception.ExceptionInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HttpClientErrorExecptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handlerException(ExceptionInterface ex){
        ExceptionResponse response = new ExceptionResponse(
                ex.getCode(),
                ex.getHttpStatus(),
                ex.getMessage()
        );
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handlerValidationException(MethodArgumentNotValidException ex){
        ExceptionResponse response = new ExceptionResponse(ValidateErrorResponse.getCode(),ValidateErrorResponse.getHttpStatusCode(),ValidateErrorResponse.getMessageResponse());
        return  ResponseEntity.status(ValidateErrorResponse.getHttpStatusCode()).body(response);
    }
}
