package br.com.recipe_finder.mapper;

import br.com.recipe_finder.DTO.request.food.FoodDescriptionRequest;
import br.com.recipe_finder.DTO.response.food.FoodSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface FoodMapper {

    @Mappings({
            @Mapping(target = "meal", source = "strMeal"),
            @Mapping(target = "category", source = "strCategory"),
            @Mapping(target = "area", source = "strArea"),
            @Mapping(target = "instructions", source = "strInstructions"),
            @Mapping(target = "ingredient1", source = "strIngredient1"),
            @Mapping(target = "ingredient2", source = "strIngredient2"),
            @Mapping(target = "ingredient3", source = "strIngredient3"),
            @Mapping(target = "ingredient4", source = "strIngredient4"),
            @Mapping(target = "ingredient5", source = "strIngredient5"),
            @Mapping(target = "ingredient6", source = "strIngredient6"),
            @Mapping(target = "ingredient7", source = "strIngredient7"),
            @Mapping(target = "ingredient8", source = "strIngredient8"),
            @Mapping(target = "ingredient9", source = "strIngredient9"),
            @Mapping(target = "ingredient10", source = "strIngredient10"),
            @Mapping(target = "ingredient11", source = "strIngredient11"),
            @Mapping(target = "ingredient12", source = "strIngredient12"),
            @Mapping(target = "ingredient13", source = "strIngredient13"),
            @Mapping(target = "ingredient14", source = "strIngredient14"),
            @Mapping(target = "ingredient15", source = "strIngredient15"),
            @Mapping(target = "ingredient16", source = "strIngredient16"),
            @Mapping(target = "ingredient17", source = "strIngredient17"),
            @Mapping(target = "ingredient18", source = "strIngredient18"),
            @Mapping(target = "ingredient19", source = "strIngredient19"),
            @Mapping(target = "ingredient20", source = "strIngredient20"),
            @Mapping(target = "measure1", source = "strMeasure1"),
            @Mapping(target = "measure2", source = "strMeasure2"),
            @Mapping(target = "measure3", source = "strMeasure3"),
            @Mapping(target = "measure4", source = "strMeasure4"),
            @Mapping(target = "measure5", source = "strMeasure5"),
            @Mapping(target = "measure6", source = "strMeasure6"),
            @Mapping(target = "measure7", source = "strMeasure7"),
            @Mapping(target = "measure8", source = "strMeasure8"),
            @Mapping(target = "measure9", source = "strMeasure9"),
            @Mapping(target = "measure10", source = "strMeasure10"),
            @Mapping(target = "measure11", source = "strMeasure11"),
            @Mapping(target = "measure12", source = "strMeasure12"),
            @Mapping(target = "measure13", source = "strMeasure13"),
            @Mapping(target = "measure14", source = "strMeasure14"),
            @Mapping(target = "measure15", source = "strMeasure15"),
            @Mapping(target = "measure16", source = "strMeasure16"),
            @Mapping(target = "measure17", source = "strMeasure17"),
            @Mapping(target = "measure18", source = "strMeasure18"),
            @Mapping(target = "measure19", source = "strMeasure19"),
            @Mapping(target = "measure20", source = "strMeasure20"),
    })
    public FoodSearchResponse toDTO(FoodDescriptionRequest request);

}