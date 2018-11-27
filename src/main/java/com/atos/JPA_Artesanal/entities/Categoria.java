package com.atos.JPA_Artesanal.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
@Table(name = "categoria")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private String descr;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "categoria")
	private List<Product> products;

	public Categoria() {
	}

	public Categoria(String codi, String string) {
		this.codigo = codi;
		this.descr = string;
		// TODO Auto-generated constructor stub
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategoria(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategoria(null);

		return product;
	}

}