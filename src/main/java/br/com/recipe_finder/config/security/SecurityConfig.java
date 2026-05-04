package br.com.recipe_finder.config.security;

import br.com.recipe_finder.entity.Permissions;
import br.com.recipe_finder.entity.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConfig jwtAuthConfig;

    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/register").permitAll()
                        .requestMatchers("/api/auth/authentication").permitAll()

                        .requestMatchers("/api/user/**").hasAnyRole(Roles.ADMIN.name(),Roles.USER.name())
                        .requestMatchers(HttpMethod.PUT,"/api/user/updateRole").hasAnyAuthority(Permissions.USER_PUT.name())

                        .requestMatchers(HttpMethod.GET,"/api/externalApi/searchDrink/*").hasAnyRole(Roles.ADMIN.name(), Roles.BARMAN.name())
                        .requestMatchers(HttpMethod.GET,"/api/externalApi/searchFood/*").hasAnyRole(Roles.ADMIN.name(), Roles.CHEF.name())

                        .requestMatchers(HttpMethod.GET, "/api/user/favDrink").hasRole(Roles.BARMAN.name())
                        .requestMatchers(HttpMethod.GET, "/api/user/listDrinksFav").hasRole(Roles.BARMAN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/user/deleteFavDrink/*").hasRole(Roles.BARMAN.name())

                        .requestMatchers(HttpMethod.GET,"/api/user/favFood").hasAnyRole(Roles.CHEF.name())
                        .requestMatchers(HttpMethod.GET,"/api/user/listFoodsFav").hasAnyRole(Roles.CHEF.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/user/deleteFavFood/*").hasRole(Roles.CHEF.name())

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthConfig, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
