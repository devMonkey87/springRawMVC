package com.atos.JPA_Artesanal.service;

import java.util.List;

import com.atos.JPA_Artesanal.entities.Cusome;

public interface CategoriaService {

	public List<Cusome> showAllCategories();

	public void insert(Cusome categoria);

	public void update(Cusome categoria);

	public void delete(Cusome categoria);

	public Cusome findById(Cusome categoria);

}
