package com.atos.JPA_Artesanal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.JPA_Artesanal.dao.CustomerDao;
import com.atos.JPA_Artesanal.entities.Customer;
import com.atos.JPA_Artesanal.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Override
	public List<Customer> showAllCustomers() {
		return null;
	}

	@Override
	public void insert(Customer customer) {

	}

	@Override
	public void update(Customer customer) {

	}

	@Override
	public void delete(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public Customer findById(Customer customer) {
		return null;
	}

}
