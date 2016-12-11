package jhc.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import clases.Product;
import clases.User;

public class ProductManager {
	private EntityManagerFactory emf;

	public ProductManager() {

	}

	public ProductManager(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManager getEntityManager() {
		if (emf == null) {
			throw new RuntimeException(
					"The EntityManagerFactory is null.  This must be passed in to the constructor or set using the setEntityManagerFactory() method.");
		}
		return emf.createEntityManager();
	}
	//Obtiene el id del vendedor de un producto
	public int getOwnerbyID(int id)
	{
		Product product;
		EntityManager em = getEntityManager();
		try {
			product = (Product) em.find(Product.class, id);
		} finally {
			em.close();
		}
		return product.getUser().getId();
	}


}
