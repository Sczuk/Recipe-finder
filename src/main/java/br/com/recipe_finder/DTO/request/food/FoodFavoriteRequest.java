package br.com.recipe_finder.DTO.request.food;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class FoodFavoriteRequest {

    @Min(value = 10000,message = "needs a id")
    private Integer idFood;
}
