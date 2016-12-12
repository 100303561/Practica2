package clases;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
@NamedQueries({
@NamedQuery(name="Cataloge", query = "select a from Product a"),
@NamedQuery(name="ShowMyProduct", query="select a from Product a where a.user=:idUser"),
@NamedQuery(name="UpdateNameProduct", query="update Product a set a.Product_Name=:name where a.ID=:id "),
@NamedQuery(name="UpdateCategory", query="update Product a set a.Category=:category where a.ID=:id "),
@NamedQuery(name="UpdatePrice", query="update Product a set a.Price=:price where a.ID=:id "),
@NamedQuery(name="UpdateDescription", query="update Product a set a.Description=:description where a.ID=:id "),
@NamedQuery(name="UpdateStatus", query="update Product a set a.status=:status where a.ID=:id "),
@NamedQuery(name="DeleteProduct", query="delete from Product a where a.ID=:id"),
@NamedQuery(name="AdvancedSearch", query="select p from Product p join p.user owner WHERE ((p.Product_Name LIKE :search OR p.Description LIKE :search OR :search IS NULL) AND (p.Category=:category or :category IS NULL) AND (owner.city = :city OR :city IS NULL) AND (owner.name LIKE :owner OR :owner IS NULL) ) ")
})
public class Product implements Serializable {
	
	public Product(int iD, String product_Name, String category, String description, byte[] imagen, double price,
			User user, Status status) {
		ID = iD;
		Product_Name = product_Name;
		Category = category;
		Description = description;
		this.imagen = imagen;
		Price = price;
		this.user = user;
		this.status = status;
	}

	private static final long serialVersionUID = 1L;

	@Id
	private int ID;
	@Column(name = "Product_Name")
	private String Product_Name;
	@Column(name = "Category")
	private String Category;
	@Column(name = "Description")
	private String Description;
	@Column(name = "Image")
	@Lob
	private byte[] imagen;
	@Column(name = "Price")
	private double Price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_User")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Status")
	private Status status;

	public Product() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getProduct_Name() {
		return Product_Name;
	}

	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Product [ID=" + ID + ", Product_Name=" + Product_Name + ", Category=" + Category + ", Description="
				+ Description + ", imagen=" + Arrays.toString(imagen) + ", Price=" + Price + ", user=" + user
				+ ", status=" + status + "]";
	}

	// public int getID_Status() {
	// return ID_Status;
	// }
	//
	// public void setID_Status(int iD_Status) {
	// ID_Status = iD_Status;
	// }
	//
	// public int getID_User() {
	// return ID_User;
	// }
	//
	// public void setID_User(int iD_User) {
	// ID_User = iD_User;
	// }

}
