package com.atos.JPA_Artesanal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atos.JPA_Artesanal.entities.Categoria;
import com.atos.JPA_Artesanal.service.impl.CategoriaServiceImpl;

import net.bytebuddy.utility.RandomString;

@RestController
public class MainController {

	@Autowired
	CategoriaServiceImpl categoriaService;

	@RequestMapping(value = "/categorias", method = RequestMethod.GET)
	public List<Categoria> muestraCategorias() {

		return categoriaService.showAllCategories();

	}

	@RequestMapping(value = "/nueva", method = RequestMethod.GET)
	public void añade() {

		Categoria categoria = new Categoria();

		String codigoAleatorio = RandomString.make(8);

		categoria.setCodigo(codigoAleatorio);
		categoria.setDescr("ninguna");

		categoriaService.insert(categoria);
	}

//	@RequestMapping(value = "/borra/{codigo}", method = RequestMethod.DELETE)
//	public void borra(@PathVariable("codigo") String codigo) {
//
//		Categoria categoria = new Categoria(codigo, "vacio");
//
//		categoriaService.delete(categoria);
//
//	}

	@RequestMapping(value = "/borra", method = RequestMethod.DELETE)
	public void borra2(@RequestParam("codigo") String codigo) {

		System.out.println("!!!!!!!!!!!!!!!!!" + codigo);

		Categoria cat = new Categoria(codigo, "vacio");

		Categoria categoria = categoriaService.findById(cat);

		categoriaService.delete(categoria);

	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@RequestParam String codigo, String desc)

	{

		Categoria cat = new Categoria(codigo, desc);

		Categoria categoria = categoriaService.findById(cat);

		categoria.setDescr(desc);

		categoriaService.update(categoria);

	}

}
