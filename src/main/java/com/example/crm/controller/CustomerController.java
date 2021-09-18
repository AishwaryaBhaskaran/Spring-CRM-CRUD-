package com.example.crm.controller;
	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.data.domain.Page;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestParam;

import com.example.crm.model.Customer;
import com.example.crm.service.CustomerService;

	@Controller
	public class CustomerController {

		@Autowired
		private CustomerService customerService;
		
		
		@GetMapping("/")
		public String viewHomePage(Model model) {
			return findPaginated(1, "firstName", "asc", model);		
		}
		
		@GetMapping("/showNewCustomerForm")
		public String showNewEmployeeForm(Model model) {
			// create model attribute to bind form data
			Customer customer = new Customer();
			model.addAttribute("customer", customer);
			return "new_customer";
		}
		
		@PostMapping("/saveCustomer")
		public String saveCustomer(@ModelAttribute("customer") Customer customer) {
			
			customerService.saveCustomer(customer);
			return "redirect:/";
		}
		
		@GetMapping("/showFormForUpdate/{id}")
		public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
			
			
			Customer customer = customerService.getCustomerById(id);
			
			
			model.addAttribute("customer", customer);
			return "update_customer";
		}
		
		@GetMapping("/deleteCustomer/{id}")
		public String deleteCustomer(@PathVariable (value = "id") long id) {
			
			
			this.customerService.deleteCustomerById(id);
			return "redirect:/";
		}
		
		
		@GetMapping("/page/{pageNo}")
		public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model) {
			int pageSize = 5;
			
			Page<Customer> page = customerService.findPaginated(pageNo, pageSize, sortField, sortDir);
			List<Customer> listCustomers = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			
			model.addAttribute("listCustomers", listCustomers);
			return "index";
		}
	}



