package br.com.recipe_finder.DTO.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidateErrorResponse {

    private String campo;
    private String message;

    private static final String messageResponse = "Error in the entry datas";
    private static final String code = "FIELD_VALIDATE_ERROR";
    private static final int httpStatusCode = HttpStatus.BAD_REQUEST.value();

    public ValidateErrorResponse(String campo, String message){
        this.campo = campo;
        this.message = message;
    }

    public static String getMessageResponse() {
        return messageResponse;
    }

    public static String getCode() {
        return code;
    }

    public static int getHttpStatusCode(){
        return httpStatusCode;
    }
}
