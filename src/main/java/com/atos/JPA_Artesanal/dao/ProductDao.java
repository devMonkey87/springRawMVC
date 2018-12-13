package com.atos.JPA_Artesanal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atos.JPA_Artesanal.entities.Product;

@Repository
@Transactional
public class ProductDao {

	@PersistenceContext(unitName = "LandettiPU")
	protected EntityManager em;

	public List<Product> listProducts() {

		// Muy importante aqui la consulta en HQL, se ha de escribir tal y como esté en
		// la clase Entidad
		// en este caso Product, con P mayúscula
		List<Product> productos = em.createQuery("from Product").getResultList();
		return productos;

	}

	public void insert(Product producto) {

		try {
			em.persist(producto);

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

			// em.detach(cat);

			em.merge(producto);
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
			Product p = em.getReference(Product.class, produkt.getReferencia());
			em.remove(p);

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

		return em.find(Product.class, product.getReferencia());

	}

	public List<Product> findByName(String nombre) {
		List<Product> productos = em.createQuery(
				"Select referencia, descripcion, nombre from  Product where nombre LIKE UPPER('%" + nombre + "%')")
				.getResultList();
		return productos;
	}

}
