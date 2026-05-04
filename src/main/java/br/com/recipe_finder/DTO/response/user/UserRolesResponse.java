package br.com.recipe_finder.DTO.response.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRolesResponse {

    private List<RolesResponse> roles;

}
