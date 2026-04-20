package br.com.recipe_finder.mapper;

import br.com.recipe_finder.DTO.request.drink.DrinkDescriptionRequest;
import br.com.recipe_finder.DTO.response.drink.DrinkDescriptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface DrinkMapper {

    @Mappings({
            @Mapping(target = "drink", source = "strDrink"),
            @Mapping(target = "IBA", source = "strIBA"),
            @Mapping(target = "glass", source = "strGlass"),
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
            @Mapping(target = "measure15", source = "strMeasure15")
    })
    public DrinkDescriptionResponse toDTO(DrinkDescriptionRequest request);

}
