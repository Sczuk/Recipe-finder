package br.com.recipe_finder.service.externalApi.theCocktailDb;

import br.com.recipe_finder.DTO.request.drink.DrinkDescriptionRequest;
import br.com.recipe_finder.DTO.request.drink.DrinkRequest;
import br.com.recipe_finder.DTO.response.drink.DrinkDescriptionResponse;
import br.com.recipe_finder.DTO.response.drink.DrinkFavoritesResponse;
import br.com.recipe_finder.DTO.response.drink.DrinkIdNameResponse;
import br.com.recipe_finder.DTO.response.drink.DrinkResponse;
import br.com.recipe_finder.exception.DrinkNotFoundException;
import br.com.recipe_finder.mapper.DrinkIdNameMapper;
import br.com.recipe_finder.mapper.DrinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchDrinkById {

    @Autowired
    private DrinkIdNameMapper mapper;

    public DrinkFavoritesResponse execute(int idDrink){
        RestTemplate rt = new RestTemplate();
        List<DrinkIdNameResponse> listDrinks = new ArrayList<>();
        String url = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i="+idDrink;
        DrinkRequest request = rt.getForObject(url,DrinkRequest.class);
        if(request.getDrinks() == null){
            throw new DrinkNotFoundException("id drink not found");
        }
        for(DrinkDescriptionRequest searchRequest : request.getDrinks()){
            DrinkIdNameResponse searchResponse = mapper.toDTO(searchRequest);
            listDrinks.add(searchResponse);
        }

        return new DrinkFavoritesResponse(listDrinks);
    }

}
