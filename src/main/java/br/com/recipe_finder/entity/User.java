package br.com.recipe_finder.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class User implements UserDetails{ //o userDetails é a classe que vai mostrar para o spring o usuario que sera autenticado
                                          //passando a senha o username a role e etc
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) //esse fetch ele deixa a role "carregada" sem que eu precise fazer uma nova busca no banco, para capturar as roles
    @JoinTable(
            name = "roles_user",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_roles")
    )
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @ManyToMany()
    @JoinTable(
            name = "drink_food_fav",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_drink")
    )
    private List<Drink> drinksFav;

    @ManyToMany()
    @JoinTable(
            name = "drink_food_fav",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_food")
    )
    private List<Food> foodsFav;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(this.roles != null) {
            for (Role role : this.roles) {
                authorities.addAll(role.getRole().getAuthorities());
            }
        }else{
            authorities.add(new SimpleGrantedAuthority(Roles.USER.getAuthorities().toString()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
