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
@RequestMapping("categoria")
public class CategoriaController {

	@Autowired
	CategoriaServiceImpl categoriaService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public void muestraCategorias() {

		List<Categoria> categorias = categoriaService.showAllCategories();

		System.out.println("LISTADO DE ENTRADAS EN TABLA *CATEGORIA* ");
		for (Categoria categoria : categorias) {
			// System.out.println("CATEGORIA : " + categoria.getCodigo() + " DESCRIP: " +
			// categoria.getDescr());

			System.out.format("%24s%24s", categoria.getCodigo(), categoria.getDescr() + "\n");
		}

	}

	@RequestMapping(value = "/nueva", method = RequestMethod.GET)
	public void añade() {

		Categoria categoria = new Categoria();

		String codigoAleatorio = RandomString.make(3);

		categoria.setCodigo(codigoAleatorio);
		categoria.setDescr("No categorizado");

		categoriaService.insert(categoria);
	}

	@RequestMapping(value = "/borra", method = RequestMethod.DELETE)
	public void borra2(@RequestParam("codigo") String codigo) {

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
