package br.com.recipe_finder.service.externalApi.theMealDb;

import br.com.recipe_finder.DTO.request.food.FoodDescriptionRequest;
import br.com.recipe_finder.DTO.request.food.FoodResquest;
import br.com.recipe_finder.DTO.response.food.FoodResponse;
import br.com.recipe_finder.DTO.response.food.FoodSearchResponse;
import br.com.recipe_finder.exception.FoodNotFoundException;
import br.com.recipe_finder.mapper.DrinkIdNameMapper;
import br.com.recipe_finder.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchFoodById {

    @Autowired
    private FoodMapper mapper;

    public FoodResponse execute(int id){
        List<FoodSearchResponse> listMeals = new ArrayList<>();
        RestTemplate rt = new RestTemplate();
        String url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i="+id;
        FoodResquest request = rt.getForObject(url, FoodResquest.class);
        if (request.getMeals() == null){
            throw new FoodNotFoundException("id food not found");
        }
        for(FoodDescriptionRequest searchRequest : request.getMeals()){
            FoodSearchResponse searchResponse = mapper.toDTO(searchRequest);
            listMeals.add(searchResponse);
        }
        return new FoodResponse(listMeals);
    }

}
