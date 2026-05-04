package br.com.recipe_finder.controller;

import br.com.recipe_finder.DTO.request.drink.DrinkFavoriteRequest;
import br.com.recipe_finder.DTO.request.food.FoodFavoriteRequest;
import br.com.recipe_finder.DTO.request.user.UpdateRoleUserRequest;
import br.com.recipe_finder.DTO.response.drink.DrinkFavoritesResponse;
import br.com.recipe_finder.DTO.response.food.FoodFavoriteResponse;
import br.com.recipe_finder.DTO.response.user.UserRolesResponse;
import br.com.recipe_finder.service.user.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UpdateRole updateRole;

    @Autowired
    private FavoriteDrink favoriteDrink;

    @Autowired
    private ListDrinksFav listDrinksFav;

    @Autowired
    private FavoriteFood favoriteFood;

    @Autowired
    private ListFoodFav listFoodFav;

    @Autowired
    private DeleteFavoriteFood deleteFavoriteFood;

    @Autowired
    private DeleteFavoriteDrink deleteFavoriteDrink;

    @PutMapping("/updateRole")
    public ResponseEntity<UserRolesResponse> updateRoleUser(@Valid @RequestBody UpdateRoleUserRequest request, Authentication auth){
        return ResponseEntity.ok(updateRole.execute(request,auth.getName()));
    }

    @GetMapping("/favDrink")
    public ResponseEntity<DrinkFavoritesResponse> favoriteDrink(@Valid @RequestBody DrinkFavoriteRequest request, Authentication auth){
        return ResponseEntity.ok(favoriteDrink.execute(request, auth.getName()));
    }

    @GetMapping("/listDrinksFav")
    public ResponseEntity<DrinkFavoritesResponse> listDrinks(Authentication auth){
        return ResponseEntity.ok(listDrinksFav.execute(auth.getName()));
    }

    @DeleteMapping("/deleteFavDrink/{id}")
    public ResponseEntity<Boolean> deleteFavDrink(@PathVariable int id , Authentication auth){
        return ResponseEntity.ok(deleteFavoriteDrink.execute(id, auth.getName()));
    }

    @GetMapping("/favFood")
    public ResponseEntity<FoodFavoriteResponse> favoriteFood(@Valid @RequestBody FoodFavoriteRequest request, Authentication auth){
        return ResponseEntity.ok(favoriteFood.execute(request, auth.getName()));
    }

    @GetMapping("/listFoodsFav")
    public ResponseEntity<FoodFavoriteResponse> listFoods(Authentication auth){
        return ResponseEntity.ok(listFoodFav.execute(auth.getName()));
    }

    @DeleteMapping("/deleteFavFood/{id}")
    public ResponseEntity<Boolean> deleteFavFood(@PathVariable int id , Authentication auth){
        return ResponseEntity.ok(deleteFavoriteFood.execute(id,auth.getName()));
    }


}
