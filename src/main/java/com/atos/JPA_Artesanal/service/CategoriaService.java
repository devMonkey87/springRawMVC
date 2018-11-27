package com.atos.JPA_Artesanal.service;

import java.util.List;

import com.atos.JPA_Artesanal.entities.Categoria;

public interface CategoriaService {

	public List<Categoria> showAllCategories();

	public void insert(Categoria categoria);

	public void update(Categoria categoria);

	public void delete(Categoria categoria);

	public Categoria findById(Categoria categoria);

}
