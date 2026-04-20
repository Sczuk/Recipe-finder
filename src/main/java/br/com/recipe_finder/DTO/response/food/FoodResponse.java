package br.com.recipe_finder.DTO.response.food;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodResponse {

    private List<FoodSearchResponse> meals;

    public FoodResponse(List<FoodSearchResponse> meals){
        this.meals = meals;
    }

}
