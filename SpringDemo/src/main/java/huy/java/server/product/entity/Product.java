package huy.java.server.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


// @Table(name="something") only used when table name is different from entity name
@Entity
@Table
public class Product {
	
	@Id
	private int id;
	
	private String name;
	
	@Column(name="description")
	private String desc;
	
	private double price;
	
	public Product() {}
	
	public Product(int inputID, String inputName, String inputDesc, double d) {
		id = inputID;
		name = inputName;
		desc = inputDesc;
		price = d;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
