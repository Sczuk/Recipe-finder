package br.com.recipe_finder.repository;

import br.com.recipe_finder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByLogin(String login);

}
