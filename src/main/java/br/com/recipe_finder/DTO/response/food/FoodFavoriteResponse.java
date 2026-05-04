package br.com.recipe_finder.DTO.response.food;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodFavoriteResponse {

    private List<FoodIdNameResponse> favorites;

    public FoodFavoriteResponse(List<FoodIdNameResponse> favorites){
        this.favorites = favorites;
    }
}
