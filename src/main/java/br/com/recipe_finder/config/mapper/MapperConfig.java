package br.com.recipe_finder.config.mapper;

import br.com.recipe_finder.mapper.DrinkMapper;
import br.com.recipe_finder.mapper.FoodMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public FoodMapper foodMapper(){
        return Mappers.getMapper(FoodMapper.class);
    }

    @Bean
    public DrinkMapper drinkMapper(){
        return Mappers.getMapper(DrinkMapper.class);
    }
}
