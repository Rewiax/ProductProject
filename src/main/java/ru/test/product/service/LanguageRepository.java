package ru.test.product.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test.product.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer>{

}
