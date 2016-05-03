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
import com.walmart.assignment.entity.Reservation;
import com.walmart.assignment.service.impl.CustomerServiceImpl;
import com.walmart.assignment.service.impl.ReservationServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class ReservationServiceTest {

	@Resource(name="reservationService")
	protected ReservationServiceImpl reservationService;

	@Resource(name="customerService")
	protected CustomerServiceImpl customerService;

	@Test
	public void testGetAll() {
		List<Reservation> reservations = reservationService.getAll();
		assertNotNull(reservations);		
	}

	@Test
	public void testAdd() {
		Customer customer = customerService.add("Faraz", "Rafi", "farazrafi@mail.com");
		Reservation reservation = reservationService.add(customer);
		assertNotNull(reservation);
	}

	@Test
	public void testDelete() {
		Customer customer = customerService.add("Faraz", "Rafi", "farazrafi@mail.com");
		Reservation reservation = reservationService.add(customer);
		reservationService.delete(reservation.getReservationId());
		reservation = reservationService.findById(reservation.getReservationId());
		assertNull(reservation);
	}

	@Test
	public void testEdit() {
		Customer customer1 = customerService.add("Faraz", "Rafi", "farazrafi@mail.com");
		Reservation reservation = reservationService.add(customer1);
		Customer customer2 = customerService.add("Faraz", "Rafi", "farazrafi@mail.com");
		reservationService.edit(reservation.getReservationId(), customer2, "CONF-NEWCODE");
		assertTrue(true);
	}

}
