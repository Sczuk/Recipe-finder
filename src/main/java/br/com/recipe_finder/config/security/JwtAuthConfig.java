package br.com.recipe_finder.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthConfig extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String login;
        if(authHeader == null ||!authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response); //vai validar o se o tipo do token esta certo
            return;
        }
        jwtToken = authHeader.substring(7);
        login = jwtService.extractLogin(jwtToken);//para tirar o login do token
        if(login != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(login); //aqui eu vou carregar o usuario do banco de dados
            if(jwtService.isTokenValid(jwtToken,userDetails)){ //vou validar o token, ver se ele nao esta expirado ou se o login bate
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, //aqui eu vou passar o login do usuario, e as roles dele, para o token ja "autenticado"
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request) //aqui eu seto informaçoes extras da sessao como o ip do usuario e a sessao (sessao é um "id" para lembrar quem é vc)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken); //aqui eu seto o contexto da minha autenticaçao, mostro para o spring que esse usuario esta autenticado
            }
        }
        filterChain.doFilter(request,response); //envia para a corrente de filtro o req e o res atualizado
    }

}
