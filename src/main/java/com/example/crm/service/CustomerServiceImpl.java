package com.example.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.crm.model.Customer;
import com.example.crm.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		this.customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(long id) {
			Optional<Customer> optional =customerRepository.findById(id);
			Customer customer = null;
			if (optional.isPresent()) {
				customer = optional.get();
			} else {
				throw new RuntimeException(" Customer not found for id :: " + id);
			}
			return customer;
		}
		// TODO Auto-generated method stub
	

	@Override
	public void deleteCustomerById(long id) {
		// TODO Auto-generated method stub
		this.customerRepository.deleteById(id);
		
	}

	@Override
	public Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.customerRepository.findAll(pageable);
	}

}
