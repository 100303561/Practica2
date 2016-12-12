package jhc.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import clases.User;

//@SuppressWarnings("unchecked")

//CAMBIAR NOMBRE DE LA CLASE Y DEL PAQUETE

public class PersonaManager {

	private EntityManagerFactory emf;

	public PersonaManager() {

	}

	public PersonaManager(EntityManagerFactory emf) {
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

	public String createPersona(User persona) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(persona);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	public String deletePersona(User persona) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			persona = em.merge(persona);
			em.remove(persona);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	public String updatePersona(User persona) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			persona = em.merge(persona);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	public User findPersonaByNombre(String nombre) {
		User persona = null;
		EntityManager em = getEntityManager();
		try {
			persona = (User) em.find(User.class, nombre);
		} finally {
			em.close();
		}
		return persona;
	}

	public User getNewPersona() {

		User persona = new User();

		return persona;
	}

}