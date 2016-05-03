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
import com.walmart.assignment.entity.SeatHold;
import com.walmart.assignment.service.impl.CustomerServiceImpl;
import com.walmart.assignment.service.impl.ReservationServiceImpl;
import com.walmart.assignment.service.impl.SeatHoldServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class SeatHoldServiceTest {

	@Resource(name="reservationService")
	protected ReservationServiceImpl reservationService;

	@Resource(name="customerService")
	protected CustomerServiceImpl customerService;

	@Resource(name="seatHoldService")
	protected SeatHoldServiceImpl seatHoldService;
	
	@Test
	public void testGetAll() {
		List<SeatHold> seatHolds = seatHoldService.getAll();
		assertNotNull(seatHolds);		
	}

	@Test
	public void testAdd() {
		Customer customer = customerService.add("Faraz", "Rafi", "farazrafi@mail.com");
		Reservation reservation = reservationService.add(customer);
		SeatHold seatHold = seatHoldService.add(reservation, "HELD");
		assertNotNull(seatHold);
	}

	@Test
	public void testDelete() {
		Customer customer = customerService.add("Faraz", "Rafi", "farazrafi@mail.com");
		Reservation reservation = reservationService.add(customer);
		SeatHold seatHold = seatHoldService.add(reservation, "HELD");
		seatHoldService.delete(seatHold.getSeatHoldId());
		seatHold = seatHoldService.findById(seatHold.getSeatHoldId());
		assertNull(seatHold);
	}

	@Test
	public void testEdit() {
		Customer customer = customerService.add("Faraz", "Rafi", "farazrafi@mail.com");
		Reservation reservation = reservationService.add(customer);
		SeatHold seatHold = seatHoldService.add(reservation, "HELD");
		seatHoldService.edit(seatHold.getSeatHoldId(), reservation, "RESERVED");
		assertTrue(true);
	}

}
