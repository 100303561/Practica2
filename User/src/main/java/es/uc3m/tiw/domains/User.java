package es.uc3m.tiw.domains;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;



//@NamedQueries({
//@NamedQuery(name="UserEmail", query="select a from User a where a.email=:email"),
//@NamedQuery(name="UpdateName", query="update User a set a.name=:name where a.id=:id "),
//@NamedQuery(name="UpdateSurname", query="update User a set a.surname=:surname where a.id=:id "),
//@NamedQuery(name="UpdatePassword", query="update User a set a.password=:password where a.id=:id "),
//@NamedQuery(name="UpdateCity", query="update User a set a.city=:city where a.id=:id "),
//@NamedQuery(name="UserLogin", query="select a from User a where (a.email=:email and a.password=:password)"),
//@NamedQuery(name="DeleteUser", query="delete from User a where a.id=:id"),
//@NamedQuery(name="AdminUsers", query = "select a from User a")
//})


//"select Email, Password from tiw.User where Email = '" + email + "' and Password = '" + password + "'");
//@Table(name="User")
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
//	@GeneratedValue
	@Column(name="id")
	private int id;
	
//	@Column(name="Name")
	private String name;
//	@Column(name="Surname")
	private String surname;
//	@Column(name="Email", unique=true)
	private String email;
//	@Column(name="Password")
	private String password;
//	@Column(name="City")
	private String city;
	
//	@OneToMany(cascade={CascadeType.ALL}, mappedBy = "user")
//	private List<Product> product;
	
	
	
	public User() {
	}
	
	public User(int id, String name, String surname, String email, String password, String city) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", password="
				+ password + ", city=" + city + "]";
	}

//	public List<Product> getProduct() {
//		return product;
//	}
//
//	public void setProduct(List<Product> product) {
//		this.product = product;
//	}
//	
}

