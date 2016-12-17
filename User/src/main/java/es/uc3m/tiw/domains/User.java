package es.uc3m.tiw.domains;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos

	@Id
	@Column(name = "id")
	private int id;

	private String name;

	private String surname;

	private String email;

	private String password;

	private String city;

	private int admin = 0;

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

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
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
				+ password + ", city=" + city + ", admin=" + admin + "]";
	}

}
