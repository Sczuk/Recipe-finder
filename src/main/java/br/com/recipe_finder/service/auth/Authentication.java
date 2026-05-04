package br.com.recipe_finder.service.auth;

import br.com.recipe_finder.DTO.request.auth.AuthenticationRequest;
import br.com.recipe_finder.DTO.response.auth.AuthenticationResponse;
import br.com.recipe_finder.config.security.JwtService;
import br.com.recipe_finder.entity.Role;
import br.com.recipe_finder.entity.Roles;
import br.com.recipe_finder.exception.UserNotFoundException;
import br.com.recipe_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Authentication {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtService jwtService;


    public AuthenticationResponse execute(AuthenticationRequest request){
        authenticationManager.authenticate( //?
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        var user = repository.findByLogin(request.getLogin())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<String> roles = new ArrayList<>();
        for(Role role : user.getRoles()){
            roles.add(role.getRole().name());
            System.out.println(role.getRole());
        }

        var jwtToken = jwtService.genereteToken(roles,user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
