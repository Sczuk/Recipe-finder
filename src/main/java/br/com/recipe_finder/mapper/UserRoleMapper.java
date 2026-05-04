package br.com.recipe_finder.mapper;

import br.com.recipe_finder.DTO.request.user.UpdateRoleUserRequest;
import br.com.recipe_finder.DTO.response.user.UserRolesResponse;
import br.com.recipe_finder.entity.Role;
import br.com.recipe_finder.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserRoleMapper {

    UserRolesResponse toDTO(User user);

    Role toEntity(UpdateRoleUserRequest request);

}
