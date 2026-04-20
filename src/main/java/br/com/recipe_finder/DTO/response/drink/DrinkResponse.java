package br.com.recipe_finder.DTO.response.drink;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DrinkResponse {

    private List<DrinkDescriptionResponse> drinks;

    public DrinkResponse(List<DrinkDescriptionResponse> drinks){
        this.drinks = drinks;
    }
}
