package br.com.recipe_finder.service.externalApi.theCocktailDb;

import br.com.recipe_finder.DTO.request.drink.DrinkRequest;
import br.com.recipe_finder.DTO.request.drink.DrinkDescriptionRequest;
import br.com.recipe_finder.DTO.response.drink.DrinkResponse;
import br.com.recipe_finder.DTO.response.drink.DrinkDescriptionResponse;
import br.com.recipe_finder.exception.DrinkNotFoundException;
import br.com.recipe_finder.mapper.DrinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchDrink {

    @Autowired
    private DrinkMapper mapper;

    public DrinkResponse execute(String drink){
        RestTemplate rt = new RestTemplate();
        List<DrinkDescriptionResponse> listDrinks = new ArrayList<>();
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+drink;
        DrinkRequest request = rt.getForObject(url,DrinkRequest.class);
        if(request.getDrinks() == null){
            throw new DrinkNotFoundException("Drink not found");
        }
        for(DrinkDescriptionRequest searchRequest : request.getDrinks()){
            DrinkDescriptionResponse searchResponse = mapper.toDTO(searchRequest);
            listDrinks.add(searchResponse);
        }

        return new DrinkResponse(listDrinks);
    }

}
