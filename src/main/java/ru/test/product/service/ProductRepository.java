package ru.test.product.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
