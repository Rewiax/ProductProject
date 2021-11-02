package ru.test.product.web;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.test.product.entity.Product;
import ru.test.product.service.ProductService;

@RestController
public class AdminRestController {

	private final static Logger logger = Logger.getLogger(AdminRestController.class);
	
	private final ProductService productService;
	
	public AdminRestController(ProductService productService) {
		this.productService = productService;
	}

	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Database ERROR")
	@ExceptionHandler({Exception.class})
	private void handleError(Exception e) {
		logger.error("Handle Error", e);
	}
	
	@GetMapping("/api/product/admin/get_product_list")
	public List<Product> getProducts(){	
		return productService.getProductList();
	}
	
	@GetMapping("/api/product/admin/get_product/{id}")
	public Object getProduct(@PathVariable("id") int id){	
		Optional<Product> productOpt = productService.getProduct(id);
		if (productOpt.isPresent()) {
			return productOpt.get();
		} else {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/api/product/admin/update")
	public ResponseEntity<Product> addProduct(Product product){		
		productService.addNewProduct(product);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
	@DeleteMapping("/api/product/admin/delete/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id){		
		productService.deleteProduct(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
}
