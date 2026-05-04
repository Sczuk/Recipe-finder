package br.com.recipe_finder.repository;

import br.com.recipe_finder.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkReposistory extends JpaRepository<Drink, Integer> {
}
