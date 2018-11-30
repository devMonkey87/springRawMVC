package com.atos.JPA_Artesanal.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atos.JPA_Artesanal.entities.Categoria;
import com.atos.JPA_Artesanal.entities.Product;
import com.atos.JPA_Artesanal.service.impl.ProductServiceImpl;

import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("produs")
public class ProductController {

	private String[] toppings = { "Queso", "Pepperoni", "Olivas", "Jamón", "Leche", "Pizza", "Harina", "Plátano",
			"Café", "Horchata", "Galletas" };
	private String[] adjetivos = { "Viejo", "Pequeño", "Grande", "Picante", "Fresco", "Agridulce", "Light", "Salado",
			"Sin azúcares", "Añejo" };

	@Autowired
	ProductServiceImpl productService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Product> muestraProductos() {

		List<Product> productos = productService.showAllProducts();

		System.out.println("LISTADO DE ENTRADAS EN TABLA *PRODUCT* ");
		for (Product prod : productos) {
			// System.out.println("CATEGORIA : " + categoria.getCodigo() + " DESCRIP: " +
			// categoria.getDescr());
			System.out.format("%24s%24s", prod.getReferencia(), prod.getNombre() + "\n");
		}

//		ModelAndView mnv = new ModelAndView("main2");
//
//		mnv.addObject("products", productos);
		return productos;

	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public void añade() {

		Product producto = new Product();

		Categoria categ = new Categoria();
		categ.setCodigo("666");

		producto.setCategoria(categ);
		String codigoAleatorio = RandomString.make(5);

		producto.setReferencia(codigoAleatorio);

		String productoSacadoManga = toppings[new Random().nextInt(toppings.length)] + " "
				+ adjetivos[new Random().nextInt(adjetivos.length)];
		producto.setNombre(productoSacadoManga);

		productService.insert(producto);
	}

}
