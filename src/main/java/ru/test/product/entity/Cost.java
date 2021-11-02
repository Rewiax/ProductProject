package ru.test.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cost")
public class Cost {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Positive @NotNull
	private double value;
	
	@Column(name = "currency_id", insertable = false, updatable = false)
	private int currencyId;
	
	@Column(name = "product_id", insertable = false, updatable = false)	
	private int productId;
	
	@ManyToOne
	@JoinColumn(name = "currency_id", nullable = false, insertable = true, updatable = true)
	private Currency currency;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, insertable = true, updatable = true)
	@JsonIgnore
	private Product product;

	public int getId() {
		return id;
	}

	public double getValue() {
		return value;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public int getProductId() {
		return productId;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Product getProduct() {
		return product;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Cost [id=" + id + ", value=" + value + ", currencyId=" + currencyId + ", productId=" + productId
				+ ", currency=" + currency + "]";
	}
	
	
	
}
