package br.com.recipe_finder.repository;

import br.com.recipe_finder.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}
