package ru.test.product.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.test.product.entity.Description;

public interface DescriptionRepository extends JpaRepository<Description, Integer>{
	
	@Query("FROM Description WHERE name = :name")
	Optional<Description> getDescriptionByName(@Param("name") String name);
	
	@Query("FROM Description WHERE description = :description")
	Optional<Description> getDescriptionByDescription(@Param("description") String description);

}
