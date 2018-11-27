package com.atos.JPA_Artesanal.service;

import java.util.List;

import com.atos.JPA_Artesanal.entities.Customer;

public interface CustomerService {

	public void insert(Customer customer);

	public void update(Customer customer);

	public void delete(Customer customer);

	public Customer findById(Customer customer);

	public List<Customer> showAllCustomers();

}
