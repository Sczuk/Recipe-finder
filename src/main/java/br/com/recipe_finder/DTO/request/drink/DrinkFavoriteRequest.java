package br.com.recipe_finder.DTO.request.drink;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class DrinkFavoriteRequest {

    @Min(value = 10000,message = "needs a id")
    private int idDrink;

}
