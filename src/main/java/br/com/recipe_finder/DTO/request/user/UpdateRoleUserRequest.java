package br.com.recipe_finder.DTO.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateRoleUserRequest {

    @NotBlank(message = "needs a role")
    private String role;

}
