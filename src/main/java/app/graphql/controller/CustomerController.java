package app.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import app.graphql.data.Customer;
import app.graphql.data.CustomerInput;
import app.graphql.data.CustomerRepository;

@RestController
public class CustomerController {
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@QueryMapping
	public Iterable<Customer> customers() {
		return customerRepository.findAll();
	}
	
	@QueryMapping
	public Customer customerById(@Argument Long id) {
		return customerRepository.findById(id).orElseThrow();
	}
	
	@QueryMapping
	public Customer customerByEmail(@Argument String email) {
		return customerRepository.findCustomerByEmail(email);
	}
	
	@MutationMapping
	public Customer addCustomer(@Argument(name = "input") CustomerInput customerInput) {
		return customerRepository.save(customerInput.getCustomerEntity());
	}
}
