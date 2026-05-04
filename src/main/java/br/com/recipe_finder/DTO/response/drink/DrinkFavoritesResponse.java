package br.com.recipe_finder.DTO.response.drink;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DrinkFavoritesResponse {

    private List<DrinkIdNameResponse> favorites;

    public DrinkFavoritesResponse(List<DrinkIdNameResponse> favorites){
        this.favorites = favorites;
    }

}
