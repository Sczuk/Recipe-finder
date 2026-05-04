package br.com.recipe_finder.mapper;

import br.com.recipe_finder.DTO.request.drink.DrinkDescriptionRequest;
import br.com.recipe_finder.DTO.response.drink.DrinkFavoritesResponse;
import br.com.recipe_finder.DTO.response.drink.DrinkIdNameResponse;
import br.com.recipe_finder.entity.Drink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface DrinkIdNameMapper {


    @Mappings({
            @Mapping( target = "drink", source = "strDrink"),
            @Mapping(target = "id", source = "idDrink")})
    DrinkIdNameResponse toDTO(DrinkDescriptionRequest request);

    @Mapping(target = "name", source = "drink")
    Drink toEntity(DrinkIdNameResponse request);

    @Mapping(target = "drink", source = "name")
    DrinkIdNameResponse theEntitytoReponse(Drink drink);
}