package br.com.recipe_finder.service.user;

import br.com.recipe_finder.DTO.request.drink.DrinkFavoriteRequest;
import br.com.recipe_finder.DTO.response.drink.DrinkFavoritesResponse;
import br.com.recipe_finder.DTO.response.drink.DrinkIdNameResponse;
import br.com.recipe_finder.entity.Drink;
import br.com.recipe_finder.entity.User;
import br.com.recipe_finder.exception.DrinkNotFoundException;
import br.com.recipe_finder.exception.UserNotFoundException;
import br.com.recipe_finder.mapper.DrinkIdNameMapper;
import br.com.recipe_finder.repository.DrinkReposistory;
import br.com.recipe_finder.repository.UserRepository;
import br.com.recipe_finder.service.externalApi.theCocktailDb.SearchDrinkById;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteDrink {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrinkReposistory drinkReposistory;

    @Autowired
    private SearchDrinkById searchDrink;

    @Autowired
    private DrinkIdNameMapper mapper;

    @Transactional
    public DrinkFavoritesResponse execute(DrinkFavoriteRequest request, String login){
        User user = userRepository.findByLogin(login).orElseThrow(()-> new UserNotFoundException("Not found user"));
        List<DrinkIdNameResponse> drinkIdNameResponses = new ArrayList<>(searchDrink.execute(request.getIdDrink()).getFavorites());
        List<Drink> drinks = new ArrayList<>();

        if(user.getDrinksFav() != null){
            drinks.addAll(user.getDrinksFav());
            for (int idDrink : user.getDrinksFav().stream().map(Drink::getId).toList()) {
                if (drinkIdNameResponses.getFirst().getId() == idDrink) {
                    throw new DrinkNotFoundException("this drink it's favorited");
                }
            }
        }

        drinks.stream().map(drink -> mapper.theEntitytoReponse(drink)).forEach(drinkIdNameResponses::add);

        for(DrinkIdNameResponse drinkIdNameResponse : drinkIdNameResponses){
            drinks.add(mapper.toEntity(drinkIdNameResponse));
        }

        user.setDrinksFav(drinks);
        drinks.forEach(drinkReposistory::save);
        userRepository.save(user);
        return new DrinkFavoritesResponse(drinkIdNameResponses);
    }

}
