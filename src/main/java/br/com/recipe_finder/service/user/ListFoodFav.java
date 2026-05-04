package br.com.recipe_finder.service.user;

import br.com.recipe_finder.DTO.response.food.FoodFavoriteResponse;
import br.com.recipe_finder.DTO.response.food.FoodIdNameResponse;
import br.com.recipe_finder.entity.User;
import br.com.recipe_finder.exception.FoodNotFoundException;
import br.com.recipe_finder.exception.UserNotFoundException;
import br.com.recipe_finder.mapper.FoodMapper;
import br.com.recipe_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListFoodFav {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodMapper mapper;

    public FoodFavoriteResponse execute(String login){
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException("User not found"));
        if(user.getFoodsFav()==null) {
            throw new FoodNotFoundException("No foods favorited");
        }
        List<FoodIdNameResponse> foods = user.getFoodsFav().stream()
                .map(food -> mapper.theEntitytoIdNameRespoonse(food)).toList();
        return new FoodFavoriteResponse(foods);
    }
}
