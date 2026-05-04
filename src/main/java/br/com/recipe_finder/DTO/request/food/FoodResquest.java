package br.com.recipe_finder.DTO.request.food;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FoodResquest {

    private List<FoodDescriptionRequest> meals;

}
