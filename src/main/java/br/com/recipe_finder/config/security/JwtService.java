package br.com.recipe_finder.config.security;

import br.com.recipe_finder.entity.Roles;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private final String iss = "recipeFinder";

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String extractLogin(String token){
        Algorithm algorithm = Algorithm.HMAC256(this.getSecretKey());
        try{
            return JWT.require(algorithm)
                    .withIssuer(iss)
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Date extractExpiration(String token){
        Algorithm algorithm = Algorithm.HMAC256(getSecretKey());
        try{
            return JWT.require(algorithm)
                    .withIssuer(iss)
                    .build()
                    .verify(token)
                    .getExpiresAt();
        }catch (JWTVerificationException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public String genereteToken(List<String> rolesList, UserDetails userDetails){
        Algorithm algorithm = Algorithm.HMAC256(getSecretKey());

        String[] roles = rolesList.toArray(new String[0]);

        try{
            return JWT.create()
                    .withIssuer(iss) //iss
                    .withArrayClaim("roles",roles)
                    .withSubject(userDetails.getUsername()) //sub
                    .withIssuedAt(new Date(System.currentTimeMillis())) //iat
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 50)) //exp
                    .sign(algorithm); //assinatura
        }catch (JWTCreationException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String login = this.extractLogin(token);
        boolean valid = false;
        if(login.equals(userDetails.getUsername()) && this.isTokenExpired(token)) valid = true;
        return valid;
    }

    public boolean isTokenExpired(String token){
        return this.extractExpiration(token).after(new Date());
    }

    private byte[] getSecretKey(){
        return Decoders.BASE64.decode(this.secretKey);
    }
}
