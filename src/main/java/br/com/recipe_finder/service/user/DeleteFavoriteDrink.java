package br.com.recipe_finder.service.user;

import br.com.recipe_finder.entity.Drink;
import br.com.recipe_finder.entity.Food;
import br.com.recipe_finder.entity.User;
import br.com.recipe_finder.exception.DrinksFavoriteNullException;
import br.com.recipe_finder.exception.FoodsFavoriteNullException;
import br.com.recipe_finder.exception.UserNotFoundException;
import br.com.recipe_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeleteFavoriteDrink {

    @Autowired
    private UserRepository userRepository;

    public Boolean execute(int id, String login){
        User user = userRepository.findByLogin(login).orElseThrow(()->new UserNotFoundException("User not Found"));

        if(user.getDrinksFav()==null) {
            throw new DrinksFavoriteNullException("not have drinks favorite");
        }

        List<Drink> drinks = new ArrayList<>();
        for (Drink drink : user.getDrinksFav()) {
            if (id != drink.getId()) {
                drinks.add(drink);
            }
        }

        user.setDrinksFav(drinks);
        userRepository.save(user);
        return true;
    }
}
