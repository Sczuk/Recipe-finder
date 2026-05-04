package br.com.recipe_finder.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Drink {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "drinksFav")
    private List<User> user;

}
