package com.walmart.assignment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.assignment.entity.Customer;

/**
 * Service for processing Persons
 * 
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all persons
	 * 
	 * @return a list of persons
	 */
	public List<Customer> getAll() {
		logger.debug("Retrieving all customers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Customer");
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Adds a new customer
	 * 
	 * @param firstName the first name of the customer
	 * @param lastName the last name of the customer
	 * @param money the money of the customer
	 */
	public Customer add(String firstName, String lastName, String email) {
		logger.debug("Adding new customer");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a new customer
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		
		// Save
		session.save(customer);
		return customer;
	}
	
	/**
	 * Deletes an existing customer
	 * @param id the id of the existing customer
	 */
	public void delete(Long id) {
		logger.debug("Deleting existing customer");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing customer first
		Customer customer = (Customer) session.get(Customer.class, id);
		
		// Delete 
		session.delete(customer);
	}
	
	public Customer findById(Long id) {
		logger.debug("Find existing customer");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing customer first
		Customer customer = (Customer) session.get(Customer.class, id);
		
		return customer;
	}	
	
	/**
	 * Edits an existing customer
	 * @param id the id of the existing customer
	 * @param firstName the first name of the existing customer
	 * @param lastName the last name of the existing customer
	 * @param money the money of the existing customer
	 */
	public void edit(Long id, String firstName, String lastName, String email) {
		logger.debug("Editing existing customer");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing customer via id
		Customer customer = (Customer) session.get(Customer.class, id);
		
		// Assign updated values to this customer
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		// Save updates
		session.save(customer);
	}
}
