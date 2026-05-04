package br.com.recipe_finder.DTO.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResgisterRequest {

    @Email(message = "login needs email")
    private String login;

    @NotBlank(message = "password cant be null")
    private String password;

}
