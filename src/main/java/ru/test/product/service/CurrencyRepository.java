package ru.test.product.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test.product.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer>{

}
