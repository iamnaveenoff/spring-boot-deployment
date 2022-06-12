package com.naveen.crudapp.service;

import java.util.*;

import com.naveen.crudapp.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naveen.crudapp.repository.CustomerRepository;


@Service
public class CustomerServices {

	@Autowired CustomerRepository repository;

	public Customer saveCustomer(Customer customer) {
		return repository.save(customer);
	}

	public List<Customer> getCustomerInfos(){
		return repository.findAll();
	}

	public Optional<Customer> getCustomerById(long id) {
		return repository.findById(id);
	}

	public boolean checkExistedCustomer(long id) {
		if(repository.existsById((long) id)) {
			return true;
		}
		return false;
	}

	public Customer updateCustomer(Customer customer) {
		return repository.save(customer);
	}

	public void deleteCustomerById(long id) {
		repository.deleteById(id);
	}

	public List<Customer> getCustomerDetilasList(){
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(25);
		list.add(45);
		System.out.println(list);
		return repository.getCustomerDetailsByFirstName();
	}

	public Map<String, Map<String, String>> getEmpDetails(String name){
		List<Customer> customerList = new ArrayList<>();
		Map<String, String> midDataMap = new HashMap<>();
		Map<String, Map<String, String>> finalMapResultSet = new HashMap<>();
		customerList = repository.findByFirstname(name);

		for (Customer customer: customerList) {
			midDataMap.put(customer.getAddress(),String.valueOf(customer.getAge()));
			finalMapResultSet.put(customer.getLastname(),midDataMap);
		}

		return finalMapResultSet;
	}
}
