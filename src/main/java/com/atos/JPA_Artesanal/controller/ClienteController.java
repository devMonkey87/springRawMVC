package com.atos.JPA_Artesanal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atos.JPA_Artesanal.entities.Customer;
import com.atos.JPA_Artesanal.entities.Product;
import com.atos.JPA_Artesanal.service.impl.CustomerServiceImpl;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	CustomerServiceImpl customerService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public void muestraClientes() {

		List<Customer> customers = customerService.showAllCustomers();

		System.out.println("LISTADO DE CLIENTES EN TABLA *CUSTOMER* ");
		for (Customer c : customers) {
			// System.out.println("CATEGORIA : " + categoria.getCodigo() + " DESCRIP: " +
			// categoria.getDescr());
			List<Product> productos = c.getProducts();

			System.out.format("%24s%24s", c.getFirstname() + " ", c.getLastname() + " [Productos comprados]: ");

			for (Product p : productos) {
				System.out.print(p.getNombre() + ",");
			}
			System.out.println();

		}

	}
}
