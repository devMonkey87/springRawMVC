package com.atos.JPA_Artesanal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atos.JPA_Artesanal.entities.Customer;

@Repository
@Transactional
public class CustomerDao {

	protected EntityManager em;
	private EntityManagerFactory emf = null;

	public CustomerDao() {

		emf = Persistence.createEntityManagerFactory("HibernatePU");

	}

	public List<Customer> listClientes() {

		em = getEntityManager();

		// Muy importante aqui la consulta en HQL, se ha de escribir tal y como esté en
		// la clase Entidad
		// en este caso Categoria, con C mayúscula
		List<Customer> costumers = em.createQuery("from Customer").getResultList();
		return costumers;

	}

	public void insert(Customer customer) {

		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(customer);
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

	public void update(Customer customer) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();

			// em.detach(cat);

			em.merge(customer);
			em.getTransaction().commit();
			System.out.println("Modificada entrada..." + customer.getFirstname() + " " + customer.getLastname());

		} catch (Exception ex) {
			System.out.println("Error modificando objeto : " + ex.getMessage());
			ex.printStackTrace(System.out);
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public void delete(Customer customer) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Customer cust = em.getReference(Customer.class, customer.getId());
			em.remove(cust);
			em.getTransaction().commit();

			System.out.println("Cliente borrado!:" + customer.toString());

		} catch (Exception ex) {
			System.out.println("Error modificando objeto : " + ex.getMessage());
			ex.printStackTrace(System.out);
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public Customer findById(Customer cliente) {

		em = getEntityManager();
		return em.find(Customer.class, cliente.getId());

	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();

	}
}
