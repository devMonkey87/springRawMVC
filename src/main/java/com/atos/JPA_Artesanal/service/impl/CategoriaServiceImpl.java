package com.atos.JPA_Artesanal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.JPA_Artesanal.dao.CategoriaDao;
import com.atos.JPA_Artesanal.entities.Categoria;
import com.atos.JPA_Artesanal.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaDao categDao;

	@Override
	public List<Categoria> showAllCategories() {

		return categDao.listCategorias();
	}

	@Override
	public void insert(Categoria categoria) {

		// CategoriaDao categoriaDao = new CategoriaDao();
		categDao.insert(categoria);

	}

	@Override
	public void update(Categoria categoria) {
		// CategoriaDao categoriaDao = new CategoriaDao();
		categDao.update(categoria);
	}

	@Override
	public void delete(Categoria categoria) {
		// CategoriaDao categoriaDao = new CategoriaDao();

		Categoria categoriaNew = findById(categoria);

		categDao.delete(categoriaNew);
	}

	@Override
	public Categoria findById(Categoria categoria) {

		return categDao.findById(categoria);
	}

}
