package com.example.crm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.crm.model.Customer;


public interface CustomerService {
	List<Customer> getAllCustomers();
	void saveCustomer(Customer customer);
	Customer getCustomerById(long id);
	void deleteCustomerById(long id);
	Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

