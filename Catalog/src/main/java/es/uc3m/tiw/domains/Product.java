package es.uc3m.tiw.domains;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Id
	@Column(name = "id")
	private int id;

	private String product_name;
	
	private String category;
	
	private String description;
	
	@Lob
	private byte[] imagen;
	
	private double price;

	private int user;
	
	private String userName;

	private String city;
	
	private String status;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String product_name, String category, String description, byte[] imagen, double price,
			int user, String userName, String status) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.category = category;
		this.description = description;
		this.imagen = imagen;
		this.price = price;
		this.user = user;
		this.userName = userName;
		this.city = city;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", product_name=" + product_name + ", category=" + category + ", description="
				+ description + ", price=" + price + ", user=" + user
				+ ", status=" + status + ", user_name=" + userName + "]";
	}

}
