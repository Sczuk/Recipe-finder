package br.com.recipe_finder.service.user;

import br.com.recipe_finder.entity.Food;
import br.com.recipe_finder.entity.User;
import br.com.recipe_finder.exception.FoodFavoritedException;
import br.com.recipe_finder.exception.FoodsFavoriteNullException;
import br.com.recipe_finder.exception.UserNotFoundException;
import br.com.recipe_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeleteFavoriteFood {

    @Autowired
    private UserRepository userRepository;

    public Boolean execute(int id, String login){
        User user = userRepository.findByLogin(login).orElseThrow(()->new UserNotFoundException("User not Found"));

        if(user.getFoodsFav()==null) {
            throw new FoodsFavoriteNullException("not have foods favorite");
        }

        List<Food> foods = new ArrayList<>();
        for (Food food : user.getFoodsFav()) {
            if (id != food.getId()) {
                foods.add(food);
            }
        }

        user.setFoodsFav(foods);
        userRepository.save(user);
        return true;
    }
}
