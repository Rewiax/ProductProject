package ru.test.product.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "product")
public class Product {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "create_time")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createTime;
	
	@Column(name = "update_time")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updateTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)		
	private List<Description> descriptions;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)		
	private List<Cost> costs;

	public int getId() {
		return id;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public List<Description> getDescriptions() {
		return descriptions;
	}

	public List<Cost> getCosts() {
		return costs;
	}

	public void setDescriptions(List<Description> descriptions) {
		this.descriptions = descriptions;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", createTime=" + createTime.getTimeInMillis() + ", updateTime=" + updateTime.getTimeInMillis() + ", descriptions="
				+ descriptions + ", costs=" + costs + "]";
	}	
	
	
}
