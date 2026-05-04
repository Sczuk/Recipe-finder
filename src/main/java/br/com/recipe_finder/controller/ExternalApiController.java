package br.com.recipe_finder.controller;

import br.com.recipe_finder.DTO.response.drink.DrinkResponse;
import br.com.recipe_finder.DTO.response.food.FoodResponse;
import br.com.recipe_finder.service.externalApi.theCocktailDb.SearchDrink;
import br.com.recipe_finder.service.externalApi.theCocktailDb.SearchDrinkById;
import br.com.recipe_finder.service.externalApi.theMealDb.SearchFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/externalApi")
public class ExternalApiController {

    @Autowired
    private SearchFood searchFood;

    @Autowired
    private SearchDrink searchDrink;

    @GetMapping("/searchFood/{meal}")
    public ResponseEntity<FoodResponse> searchMeal(@PathVariable String meal){
        return ResponseEntity.ok(searchFood.execute(meal));
    }

    @GetMapping("/searchDrink/{drink}")
    public ResponseEntity<DrinkResponse> searchDrink(@PathVariable String drink){
        return ResponseEntity.ok(searchDrink.execute(drink));
    }

}
