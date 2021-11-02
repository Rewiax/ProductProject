package ru.test.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.test.product.entity.Cost;
import ru.test.product.entity.Currency;
import ru.test.product.entity.Description;
import ru.test.product.entity.Language;
import ru.test.product.entity.Product;
import ru.test.product.service.ProductService;
import ru.test.product.web.AdminRestController;

@DisplayName("Test DB")
@SpringBootTest(classes = ProductProjectApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class DatabaseTest{

	private final static Logger logger = Logger.getLogger(DatabaseTest.class);
	
	private ProductService productService;
	private AdminRestController adminRestController;
	
	@Autowired
	public DatabaseTest(ProductService productService, AdminRestController adminRestController) {
		this.productService = productService;
		this.adminRestController = adminRestController;
	}

	
	
	@Test
	@Order(1)
	public void fstTest() {
		Currency currency = productService.getCurrency(1).get();
		Language language = productService.getLanguage(1).get();
		
		
		List<Cost> costs = new ArrayList<>();
		Cost cost = new Cost();
		cost.setValue(3);
		cost.setCurrency(currency);
		costs.add(cost);
		
		List<Description> descriptions = new ArrayList<>();
		Description description = new Description();
		description.setName("test name");
		description.setDescription("test description");		
		description.setLanguage(language);
		descriptions.add(description);
		
		Product product = new Product();
		cost.setProduct(product);
		description.setProduct(product);
		
		product.setCosts(costs);
		product.setDescriptions(descriptions);
//		productService.addNewProduct(product);
		adminRestController.addProduct(product);
		
		List<Product> products = productService.getProductList();
		logger.debug(products);
	}
}
