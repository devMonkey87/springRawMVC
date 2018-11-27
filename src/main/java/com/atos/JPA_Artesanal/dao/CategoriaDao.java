package com.atos.JPA_Artesanal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atos.JPA_Artesanal.entities.Categoria;

@Repository
@Transactional
public class CategoriaDao {

	@PersistenceContext
	protected EntityManager em;

	public List<Categoria> listCategorias() {

		// Muy importante aqui la consulta en HQL, se ha de escribir tal y como esté en
		// la clase Entidad
		// en este caso Categoria, con C mayúscula
		List<Categoria> categorias = em.createQuery("from Categoria").getResultList();
		return categorias;

	}

	public void insert(Categoria categoria) {

		try {
			em.persist(categoria);

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

	public void update(Categoria categoria) {
		try {

			// em.detach(cat);

			em.merge(categoria);
			System.out.println("Modificada entrada..." + categoria.getCodigo());

		} catch (Exception ex) {
			System.out.println("Error modificando objeto : " + ex.getMessage());
			ex.printStackTrace(System.out);
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public void delete(Categoria categoria) {
		try {
			Categoria cat = em.getReference(Categoria.class, categoria.getCodigo());
			em.remove(cat);

			System.out.println("Categoria borrada!:" + categoria.toString());

		} catch (Exception ex) {
			System.out.println("Error modificando objeto : " + ex.getMessage());
			ex.printStackTrace(System.out);
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public Categoria findById(Categoria categoria) {

		return em.find(Categoria.class, categoria.getCodigo());

	}
}