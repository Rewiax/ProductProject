package ru.test.product.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.test.product.entity.Product;
import ru.test.product.service.ProductService;

@RestController
public class ClientRestController {

	private final static Logger logger = Logger.getLogger(ClientRestController.class);
	
	private final ProductService productService;
	
	public ClientRestController(ProductService productService) {
		this.productService = productService;
	}

	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Database ERROR")
	@ExceptionHandler({Exception.class})
	private void handleError(Exception e) {
		logger.error("Handle Error", e);
	}
	
	@ExceptionHandler({NoDataPresentException.class})
	private Object handleNoDataError(NoDataPresentException e) {
		logger.error("Handle No Data Error", e);
		HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;
		return new ErrorData(httpStatus.value(), httpStatus.getReasonPhrase());
	}
	
	@GetMapping("/api/product/client/get_product_list")
	public List<Product> getProducts(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize, 
			@RequestParam(defaultValue = "1") Integer currencyId, @RequestParam(defaultValue = "1") Integer languageId){	
		return productService.getSortedList(pageNumber, pageSize).stream().filter(product -> {
			return (checkConditions(product, currencyId, languageId));
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/api/product/client/get_product/{id}")
	public Object getProduct(@PathVariable("id") int id, @RequestParam(defaultValue = "1") Integer currencyId, 
			@RequestParam(defaultValue = "1") Integer languageId) throws NoDataPresentException{	
		logger.debug("get id " + id + " " + currencyId + " " + languageId);
		Optional<Product> productOpt = productService.getProduct(id);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			if (checkConditions(product, currencyId, languageId)) {
				return product;
			}
		} 
		
		throw new NoDataPresentException();
	}
	
	@GetMapping("/api/product/client/get_product_by_name/{name}")
	public Object getProductByName(@PathVariable("name") String name, @RequestParam(defaultValue = "1") Integer currencyId, 
			@RequestParam(defaultValue = "1") Integer languageId){
		Optional<Product> productOpt = productService.getProductByName(name);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			if (checkConditions(product, currencyId, languageId)) {
				return product;
			}
		} 
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/api/product/client/get_product_by_description/{description}")
	public Object getProductByDescription(@PathVariable("description") String description, @RequestParam(defaultValue = "1") Integer currencyId,
			@RequestParam(defaultValue = "1") Integer languageId){
		Optional<Product> productOpt = productService.getProductByDescription(description);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			if (checkConditions(product, currencyId, languageId)) {
				return product;
			}
		} 
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	private boolean checkConditions(Product product, int currencyId, int languageId) {
		if (product.getCosts().stream().filter(cost -> cost.getCurrencyId() == currencyId).count() > 0
				&& product.getDescriptions().stream().filter(description -> description.getLanguageId() == languageId).count() > 0) {
			return true;
		}
		
		return false;
	}
}
