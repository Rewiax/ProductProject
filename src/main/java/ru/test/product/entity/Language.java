package ru.test.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "language")
public class Language {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", name=" + name + "]";
	}
	
	
}
