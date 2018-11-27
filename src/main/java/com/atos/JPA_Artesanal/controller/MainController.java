package com.atos.JPA_Artesanal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atos.JPA_Artesanal.entities.Cusome;
import com.atos.JPA_Artesanal.entities.Product;
import com.atos.JPA_Artesanal.service.impl.CategoriaServiceImpl;
import com.atos.JPA_Artesanal.service.impl.ProductServiceImpl;

import net.bytebuddy.utility.RandomString;

@RestController
public class MainController {

	@Autowired
	CategoriaServiceImpl categoriaService;

	@Autowired
	ProductServiceImpl productService;

	@RequestMapping(value = "/categorias", method = RequestMethod.GET)
	public void muestraCategorias() {

		List<Cusome> categorias = categoriaService.showAllCategories();

		System.out.println("LISTADO DE ENTRADAS EN TABLA *CATEGORIA* ");
		for (Cusome categoria : categorias) {
			// System.out.println("CATEGORIA : " + categoria.getCodigo() + " DESCRIP: " +
			// categoria.getDescr());

			System.out.format("%24s%24s", categoria.getCodigo(), categoria.getDescr() + "\n");
		}

	}

	@RequestMapping(value = "/nueva", method = RequestMethod.GET)
	public void añade() {

		Cusome categoria = new Cusome();

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

		Cusome cat = new Cusome(codigo, "vacio");

		Cusome categoria = categoriaService.findById(cat);

		categoriaService.delete(categoria);

	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@RequestParam String codigo, String desc)

	{

		Cusome cat = new Cusome(codigo, desc);

		Cusome categoria = categoriaService.findById(cat);

		categoria.setDescr(desc);

		categoriaService.update(categoria);

	}

	@RequestMapping(value = "/prods", method = RequestMethod.GET)
	public void muestraProductos() {

		List<Product> productos = productService.showAllProducts();

		System.out.println("LISTADO DE ENTRADAS EN TABLA *PRODUCT* ");
		for (Product prod : productos) {
			// System.out.println("CATEGORIA : " + categoria.getCodigo() + " DESCRIP: " +
			// categoria.getDescr());

			System.out.format("%24s%24s", prod.getReferencia(), prod.getNombre() + "\n");
		}

	}

}
