package clases;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Status")
public class Status implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Status() {
		super();
	}

	public Status(int iD, String status) {
		super();
		ID = iD;
		Status = status;
	}
	@Id
	private int ID;
	@Column(name="Status")
	private String Status;
	
	@OneToMany(cascade={CascadeType.ALL}, mappedBy = "status")
	private List<Product> product;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
}
