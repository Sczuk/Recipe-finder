package br.com.recipe_finder.DTO.request.drink;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DrinkRequest {

    private List<DrinkDescriptionRequest> drinks;

}
