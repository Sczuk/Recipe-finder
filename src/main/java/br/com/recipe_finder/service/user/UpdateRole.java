package br.com.recipe_finder.service.user;

import br.com.recipe_finder.DTO.request.user.UpdateRoleUserRequest;
import br.com.recipe_finder.DTO.response.user.UserRolesResponse;
import br.com.recipe_finder.entity.Role;
import br.com.recipe_finder.entity.Roles;
import br.com.recipe_finder.entity.User;
import br.com.recipe_finder.exception.RoleExistsException;
import br.com.recipe_finder.exception.RoleNotExistsException;
import br.com.recipe_finder.exception.UserNotFoundException;
import br.com.recipe_finder.mapper.UserRoleMapper;
import br.com.recipe_finder.repository.RoleRepository;
import br.com.recipe_finder.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UpdateRole {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleMapper mapper;


    @Transactional
    public UserRolesResponse execute(UpdateRoleUserRequest request, String login){
        User user = userRepository.findByLogin(login).orElseThrow(()->new UserNotFoundException("Login not found"));
        for(Roles roles : user.getRoles().stream().map(Role::getRole).toList()){
            if(Objects.equals(request.getRole(), roles.name())){
                throw new RoleExistsException("Role "+roles+" exists");
            }
        }

        List<Role> rolesList = new ArrayList<>(user.getRoles());

        rolesList.add(roleRepository.findByRole(mapper.toEntity(request).getRole())
                .orElseThrow(()-> new RoleNotExistsException("Role not found")));
        user.setRoles(rolesList);

        userRepository.save(user);

        return mapper.toDTO(user);
    }
}
