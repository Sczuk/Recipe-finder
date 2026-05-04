package br.com.recipe_finder.service.auth;

import br.com.recipe_finder.DTO.request.auth.AuthResgisterRequest;
import br.com.recipe_finder.DTO.response.auth.AuthenticationResponse;
import br.com.recipe_finder.config.security.JwtService;
import br.com.recipe_finder.entity.Role;
import br.com.recipe_finder.entity.Roles;
import br.com.recipe_finder.repository.RoleRepository;
import br.com.recipe_finder.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Register {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    @Transactional
    public AuthenticationResponse execute(AuthResgisterRequest request) {
        List<String> roles = new ArrayList<>();
        roles.add(Roles.USER.name());

        var userD = User.builder()
                .username(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(String.valueOf(roles))
                .build();

        br.com.recipe_finder.entity.User user = new br.com.recipe_finder.entity.User();
        user.setLogin(userD.getUsername());
        user.setPassword(userD.getPassword());
        List<Role> role = roleRepository.findByRole(Roles.USER).stream().toList();
        user.setRoles(role);

        repository.save(user);

        var jwtToken = jwtService.genereteToken(roles, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
