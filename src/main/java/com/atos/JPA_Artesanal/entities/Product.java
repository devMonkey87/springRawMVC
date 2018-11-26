package com.atos.JPA_Artesanal.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String referencia;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-many association to Customer
	@ManyToMany
	@JoinTable(
		name="cust_x_prod"
		, joinColumns={
			@JoinColumn(name="reference")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_customer")
			}
		)
	private List<Customer> customers;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="cod_categoria")
	private Categoria categoria;

	public Product() {
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}