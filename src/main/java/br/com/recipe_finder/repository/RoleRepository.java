package br.com.recipe_finder.repository;

import br.com.recipe_finder.entity.Role;
import br.com.recipe_finder.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByRole(Roles role);

}
