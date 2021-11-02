package ru.test.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.test.product.entity.Currency;
import ru.test.product.entity.Description;
import ru.test.product.entity.Language;
import ru.test.product.entity.Product;

/**
 * @author maxim
 * service for work with database
 */
@Repository
@Transactional
class ProductServiceImpl implements ProductService{
	
	private final static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	private final ProductRepository productRepository;
	
	private final CurrencyRepository currencyRepository;
	
	private final LanguageRepository languageRepository;
	
	private final DescriptionRepository descriptionRepository;
	
	public ProductServiceImpl(ProductRepository productRepository, CurrencyRepository currencyRepository, LanguageRepository languageRepository,
			DescriptionRepository descriptionRepository) {
		this.productRepository = productRepository;
		this.currencyRepository = currencyRepository;
		this.languageRepository = languageRepository;
		this.descriptionRepository = descriptionRepository;
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<Product> getProductList() {
		return productRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> getSortedList(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Product> page = productRepository.findAll(pageable);
		if (page.hasContent()) {
			return page.getContent();
		} else {
			return new ArrayList<Product>();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> getProduct(int id) {
		return productRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Product> getProductByName(String name) {
		Optional<Description> descriptionalOpt = descriptionRepository.getDescriptionByName(name);
		if (descriptionalOpt.isPresent()) {
			return productRepository.findById(descriptionalOpt.get().getProductId());
		}
		
		return Optional.empty();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Product> getProductByDescription(String description) {
		Optional<Description> descriptionalOpt = descriptionRepository.getDescriptionByDescription(description);
		if (descriptionalOpt.isPresent()) {
			return productRepository.findById(descriptionalOpt.get().getProductId());
		}
		
		return Optional.empty();
	}

	@Override
	public void addNewProduct(Product product) {
		productRepository.save(product);
	}
	
	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}
	
	@Override
	public Optional<Currency> getCurrency(int id) {
		return currencyRepository.findById(id);
	}
	
	@Override
	public Optional<Language> getLanguage(int id) {
		return languageRepository.findById(id);
	}

}
