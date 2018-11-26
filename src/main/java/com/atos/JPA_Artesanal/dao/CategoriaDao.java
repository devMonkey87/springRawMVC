package com.atos.JPA_Artesanal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atos.JPA_Artesanal.entities.Categoria;

@Repository
@Transactional
public class CategoriaDao {

	protected EntityManager em;
	private EntityManagerFactory emf = null;

	public CategoriaDao() {

		emf = Persistence.createEntityManagerFactory("HibernatePU");

	}

	public List<Categoria> listCategorias() {

		String query = "from categoria";

		em = getEntityManager();
		Query q = em.createQuery(query, Categoria.class);
		return q.getResultList();

	}

	public void insert(Categoria categoria) {

		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(categoria);
			em.getTransaction().commit();

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
			em = getEntityManager();
			em.getTransaction().begin();

			// em.detach(cat);

			em.merge(categoria);
			em.getTransaction().commit();

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
			em = getEntityManager();
			em.getTransaction().begin();
			Categoria cat = em.getReference(Categoria.class, categoria.getCodigo());
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

	public Categoria findById(Categoria categoria) {

		em = getEntityManager();
		return em.find(Categoria.class, categoria.getCodigo());

	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();

	}
}
