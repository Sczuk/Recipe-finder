package br.com.recipe_finder.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table
@Entity
@Getter
@Setter
public class Role {

    @Id
    private int idRole;

    @Column(nullable = false)
    private Roles role;

    @ManyToMany(mappedBy = "roles")
    private List<User> user;
}
