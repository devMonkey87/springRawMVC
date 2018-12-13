package com.atos.JPA_Artesanal.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atos.JPA_Artesanal.entities.Categoria;
import com.atos.JPA_Artesanal.entities.Product;
import com.atos.JPA_Artesanal.service.impl.ProductServiceImpl;

import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin
@RequestMapping("produs")
public class ProductController {

	private String[] toppings = { "Queso", "Pepperoni", "Olivas", "Jamón", "Leche", "Pizza", "Harina", "Plátano",
			"Café", "Horchata", "Galletas" };
	private String[] adjetivos = { "Viejo", "Pequeño", "Grande", "Picante", "Fresco", "Agridulce", "Light", "Salado",
			"Sin azúcares", "Añejo" };

	@Autowired
	ProductServiceImpl productService;

	// OBTIENE TODOS LOS PRODUCTOS
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Product> muestraProductos() {

		List<Product> productos = productService.showAllProducts();

////		System.out.println("LISTADO DE ENTRADAS EN TABLA *PRODUCT* ");
//		for (Product prod : productos) {
//			// System.out.println("CATEGORIA : " + categoria.getCodigo() + " DESCRIP: " +
//			// categoria.getDescr());
//			System.out.format("%24s%24s", prod.getReferencia(), prod.getNombre() + "\n");
//		}

//		ModelAndView mnv = new ModelAndView("main2");
//
//		mnv.addObject("products", productos);
		return productos;

	}

	// GENERADOR DE PRODUCTOS DE MANERA ALEATORIA
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
//AÑADE UN PRODUCTO NUEVO VIA JSON

	@RequestMapping(value = "/addP", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void addP(@RequestBody Product produc) {

		productService.insert(produc);

	}

	// BORRA PRODUCTO POR ID
	@RequestMapping(value = "/{referencia}", method = RequestMethod.DELETE)
	public void borraPorID(@PathVariable("referencia") String codigo) {

		Product product1 = new Product(codigo, "", "");

		Product productAdded = productService.findById(product1);
		System.out.println(productAdded.getNombre());
		productService.delete(productAdded);

	}

//ENCUENTRA PRODUCTO POR ID
	@RequestMapping(value = "/{referencia}", method = RequestMethod.GET)
	public Product obtienePorID(@PathVariable("referencia") String codigo) {

		Product product1 = new Product(codigo, "vacio", "vacio");

		return productService.findById(product1);

	}

	// ENCUENTRA PRODUCTO POR NOMBRE
	@RequestMapping(value = "/search/{nombre}", method = RequestMethod.GET)
	public List<Product> obtieneByName(@PathVariable("nombre") String nombre) {

		List<Product> productosTest = productService.findByName(nombre);

		return productosTest;

	}

}
