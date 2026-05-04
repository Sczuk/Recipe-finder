package br.com.recipe_finder.controller.auth;

import br.com.recipe_finder.DTO.request.auth.AuthResgisterRequest;
import br.com.recipe_finder.DTO.request.auth.AuthenticationRequest;
import br.com.recipe_finder.DTO.response.auth.AuthenticationResponse;
import br.com.recipe_finder.service.auth.Authentication;
import br.com.recipe_finder.service.auth.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private Register register;

    @Autowired
    private Authentication authentication;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> authResgister(@RequestBody AuthResgisterRequest registerRequest){
        return ResponseEntity.ok(register.execute(registerRequest));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(authentication.execute(authRequest));
    }

}
