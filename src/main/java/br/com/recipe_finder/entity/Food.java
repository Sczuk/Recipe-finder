package br.com.recipe_finder.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Food {

    @Id
    private int idFood;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "foodsFav")
    private List<User> user;

}
