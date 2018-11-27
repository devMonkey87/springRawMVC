package com.atos.JPA_Artesanal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atos.JPA_Artesanal.entities.Product;

@Repository
@Transactional
public class ProductDao {

	protected EntityManager em;
	private EntityManagerFactory emf = null;

	public ProductDao() {

		emf = Persistence.createEntityManagerFactory("HibernatePU");

	}

	public List<Product> listProducts() {

		em = getEntityManager();

		// Muy importante aqui la consulta en HQL, se ha de escribir tal y como esté en
		// la clase Entidad
		// en este caso Product, con P mayúscula
		List<Product> productos = em.createQuery("from Product").getResultList();
		return productos;

	}

	public void insert(Product producto) {

		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(producto);
			em.getTransaction().commit();

			System.out.println("Insertando nueva entrada...");
		} catch (Exception ex) {
			System.out.println("Error insertando objeto : " + ex.getMessage());
			ex.printStackTrace(System.out);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void update(Product producto) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();

			// em.detach(cat);

			em.merge(producto);
			em.getTransaction().commit();
			System.out.println("Modificada entrada..." + producto.getNombre());

		} catch (Exception ex) {
			System.out.println("Error modificando objeto : " + ex.getMessage());
			ex.printStackTrace(System.out);
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public void delete(Product produkt) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Product p = em.getReference(Product.class, produkt.getDescripcion());
			em.remove(p);
			em.getTransaction().commit();

			System.out.println("Producto borrado!:" + produkt.toString());

		} catch (Exception ex) {
			System.out.println("Error modificando objeto : " + ex.getMessage());
			ex.printStackTrace(System.out);
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public Product findById(Product product) {

		em = getEntityManager();
		return em.find(Product.class, product.getNombre());

	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();

	}
}
