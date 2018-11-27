package com.atos.JPA_Artesanal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.JPA_Artesanal.dao.CategoriaDao;
import com.atos.JPA_Artesanal.entities.Cusome;
import com.atos.JPA_Artesanal.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaDao categDao;

	@Override
	public List<Cusome> showAllCategories() {

		return categDao.listCategorias();
	}

	@Override
	public void insert(Cusome categoria) {

		// CategoriaDao categoriaDao = new CategoriaDao();
		categDao.insert(categoria);

	}

	@Override
	public void update(Cusome categoria) {
		// CategoriaDao categoriaDao = new CategoriaDao();
		categDao.update(categoria);
	}

	@Override
	public void delete(Cusome categoria) {
		// CategoriaDao categoriaDao = new CategoriaDao();

		Cusome categoriaNew = findById(categoria);

		categDao.delete(categoriaNew);
	}

	@Override
	public Cusome findById(Cusome categoria) {

		return categDao.findById(categoria);
	}

}
