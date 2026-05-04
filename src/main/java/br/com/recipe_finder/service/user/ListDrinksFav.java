package br.com.recipe_finder.service.user;

import br.com.recipe_finder.DTO.response.drink.DrinkFavoritesResponse;
import br.com.recipe_finder.DTO.response.drink.DrinkIdNameResponse;
import br.com.recipe_finder.entity.Drink;
import br.com.recipe_finder.entity.User;
import br.com.recipe_finder.exception.UserNotFoundException;
import br.com.recipe_finder.mapper.DrinkIdNameMapper;
import br.com.recipe_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListDrinksFav {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrinkIdNameMapper mapper;

    public DrinkFavoritesResponse execute(String login){
        User user = userRepository.findByLogin(login).orElseThrow(()->new UserNotFoundException("User not found"));
        List<DrinkIdNameResponse> drinkIdNameResponses = new ArrayList<>();
        if(user.getDrinksFav() != null) {
            for (Drink drink : user.getDrinksFav()) {
                drinkIdNameResponses.add(mapper.theEntitytoReponse(drink));
            }
        }
        return new DrinkFavoritesResponse(drinkIdNameResponses);
    }
}
