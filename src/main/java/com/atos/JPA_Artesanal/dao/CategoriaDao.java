package com.atos.JPA_Artesanal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atos.JPA_Artesanal.entities.Cusome;

@Repository
@Transactional
public class CategoriaDao {

	protected EntityManager em;
	private EntityManagerFactory emf = null;

	public CategoriaDao() {

		emf = Persistence.createEntityManagerFactory("HibernatePU");

	}

	public List<Cusome> listCategorias() {

		em = getEntityManager();

		// Muy importante aqui la consulta en HQL, se ha de escribir tal y como esté en
		// la clase Entidad
		// en este caso Categoria, con C mayúscula
		List<Cusome> categorias = em.createQuery("from Categoria").getResultList();
		return categorias;

	}

	public void insert(Cusome categoria) {

		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(categoria);
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

	public void update(Cusome categoria) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();

			// em.detach(cat);

			em.merge(categoria);
			em.getTransaction().commit();
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

	public void delete(Cusome categoria) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Cusome cat = em.getReference(Cusome.class, categoria.getCodigo());
			em.remove(cat);
			em.getTransaction().commit();

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

	public Cusome findById(Cusome categoria) {

		em = getEntityManager();
		return em.find(Cusome.class, categoria.getCodigo());

	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();

	}
}
