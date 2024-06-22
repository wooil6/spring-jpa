package com.springboot.coffee.repository;

import com.springboot.coffee.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    Optional<Coffee> findByCoffeeCode(String coffeeCode);


    @Query("SELECT * FROM COFFEE WHERE COFFEE_ID = coffeeId")
    Optional<Coffee> findByCoffee(long coffeeId);
}
