package ru.test.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "description")
public class Description {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String name;
	
	private String description;
	
	@Column(name = "language_id", insertable = false, updatable = false)
	private int languageId;
	
	@Column(name = "product_id", insertable = false, updatable = false)	
	private int productId;
	
	@ManyToOne
	@JoinColumn(name = "language_id", nullable = false, insertable = true, updatable = true)
	private Language language;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, insertable = true, updatable = true)
	@JsonIgnore
	private Product product;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getLanguageId() {
		return languageId;
	}

	public int getProductId() {
		return productId;
	}

	public Language getLanguage() {
		return language;
	}

	public Product getProduct() {
		return product;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Description [id=" + id + ", name=" + name + ", description=" + description + ", languageId="
				+ languageId + ", productId=" + productId + ", language=" + language + "]";
	}
	
	
}
