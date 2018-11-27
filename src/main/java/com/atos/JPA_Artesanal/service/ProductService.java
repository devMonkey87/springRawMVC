package com.atos.JPA_Artesanal.service;

import java.util.List;

import com.atos.JPA_Artesanal.entities.Product;

public interface ProductService {

	public List<Product> showAllProducts();

	public void insert(Product product);

	public void update(Product product);

	public void delete(Product product);

	public Product findById(Product product);
}
