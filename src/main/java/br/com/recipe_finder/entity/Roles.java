package br.com.recipe_finder.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum Roles {
    ADMIN(
            Set.of(
                    Permissions.ADMIN_GET,
                    Permissions.ADMIN_DELETE,
                    Permissions.ADMIN_POST,
                    Permissions.BARMAN_GET,
                    Permissions.BARMAN_POST,
                    Permissions.CHEF_GET,
                    Permissions.CHEF_POST,
                    Permissions.USER_POST,
                    Permissions.USER_DELETE,
                    Permissions.USER_GET,
                    Permissions.USER_PUT
            )
    ),
    CHEF(
            Set.of(
                    Permissions.CHEF_GET,
                    Permissions.CHEF_POST
            )
    ),
    BARMAN(
            Set.of(
                    Permissions.BARMAN_GET,
                    Permissions.BARMAN_POST
            )
    ),
    USER(
            Set.of(
                    Permissions.USER_POST,
                    Permissions.USER_DELETE,
                    Permissions.USER_GET,
                    Permissions.USER_PUT
            )
    );


    private final Set<Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = new java.util.ArrayList<>(getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
