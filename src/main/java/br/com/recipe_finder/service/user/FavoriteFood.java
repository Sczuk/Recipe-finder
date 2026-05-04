package br.com.recipe_finder.service.user;

import br.com.recipe_finder.DTO.request.food.FoodFavoriteRequest;
import br.com.recipe_finder.DTO.response.food.FoodFavoriteResponse;
import br.com.recipe_finder.DTO.response.food.FoodIdNameResponse;
import br.com.recipe_finder.DTO.response.food.FoodSearchResponse;
import br.com.recipe_finder.entity.Food;
import br.com.recipe_finder.entity.User;
import br.com.recipe_finder.exception.FoodFavoritedException;
import br.com.recipe_finder.exception.UserNotFoundException;
import br.com.recipe_finder.mapper.FoodMapper;
import br.com.recipe_finder.repository.FoodRepository;
import br.com.recipe_finder.repository.UserRepository;
import br.com.recipe_finder.service.externalApi.theMealDb.SearchFoodById;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteFood {

    @Autowired
    private SearchFoodById searchFoodById;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodMapper mapper;

    @Transactional
    public FoodFavoriteResponse execute(FoodFavoriteRequest request, String login){
        User user = userRepository.findByLogin(login).orElseThrow(()->new UserNotFoundException("User not found"));
        List<Food> foods = new ArrayList<>();
        List<FoodIdNameResponse> responses = new ArrayList<>();

        for(FoodSearchResponse foodSearchResponse : searchFoodById.execute(request.getIdFood()).getMeals()){
            responses.add(mapper.theSearchResponseToIdNameResponse(foodSearchResponse));
            foods.add(mapper.toEntity(foodSearchResponse));
        }

        if(user.getFoodsFav() != null) {
            user.getFoodsFav().stream().map(food -> mapper.theEntitytoIdNameRespoonse(food)).forEach(responses::add);
            foods.addAll(user.getFoodsFav());
            for (int id : user.getFoodsFav().stream().map(Food::getId).toList()){
                if(request.getIdFood()==id){
                    throw new FoodFavoritedException("this food it's favorited");
                }
            }
        }

        for(Food food : foods){
            if(!foodRepository.existsById(food.getId())) {
                foodRepository.save(food);
            }
        }

        user.setFoodsFav(foods);
        userRepository.save(user);

        return new FoodFavoriteResponse(responses);
    }

}
