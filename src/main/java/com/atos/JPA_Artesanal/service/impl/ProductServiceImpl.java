package com.atos.JPA_Artesanal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.JPA_Artesanal.dao.ProductDao;
import com.atos.JPA_Artesanal.entities.Product;
import com.atos.JPA_Artesanal.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> showAllProducts() {

		return productDao.listProducts();
	}

	@Override
	public void insert(Product product) {
		productDao.insert(product);

	}

	@Override
	public void update(Product product) {
		productDao.update(product);
	}

	@Override
	public void delete(Product product) {

		Product prodNew = findById(product);

		productDao.delete(prodNew);
	}

	@Override
	public Product findById(Product product) {
		return productDao.findById(product);
	}

	@Override
	public List<Product> findByName(String name) {

		return productDao.findByName(name);
	}

}
