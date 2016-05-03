package com.walmart.assignment.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.assignment.entity.Customer;
import com.walmart.assignment.service.impl.CustomerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class CustomerServiceTest {

	@Resource(name="customerService")
	protected CustomerServiceImpl customerService;

	@Test
	public void testGetAll() {
		List<Customer> customers = customerService.getAll();
		assertNotNull(customers);		
	}

	@Test
	public void testAdd() {
		Customer customer = customerService.add("Faraz", "Rafi", "farazrafi@mail.com");
		assertNotNull(customer);
	}

	@Test
	public void testDelete() {
		Customer customer = customerService.add("Faraz", "Rafi", "farazrafi@mail.com");
		customerService.delete(customer.getCustomerId());
		customer = customerService.findById(customer.getCustomerId());
		assertNull(customer);
	}

	@Test
	public void testEdit() {
		Customer customer = customerService.add("Faraz", "Rafi", "farazrafi@mail.com");
		customerService.edit(customer.getCustomerId(), "New User", "Second User", "newemail@email.com" );
		assertTrue(true);
	}

}
