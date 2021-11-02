package ru.test.product.service;

import java.util.List;
import java.util.Optional;

import ru.test.product.entity.Currency;
import ru.test.product.entity.Language;
import ru.test.product.entity.Product;

public interface ProductService {

	List<Product> getProductList();

	Optional<Product> getProduct(int id);

	void addNewProduct(Product product);

	Optional<Currency> getCurrency(int id);
	
	Optional<Language> getLanguage(int id);

	void deleteProduct(int id);

	List<Product> getSortedList(int pageNumber, int pageSize);

	Optional<Product> getProductByName(String name);

	Optional<Product> getProductByDescription(String description);
}
