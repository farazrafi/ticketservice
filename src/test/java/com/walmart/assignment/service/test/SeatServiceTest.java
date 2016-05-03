package com.walmart.assignment.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.assignment.entity.Level;
import com.walmart.assignment.entity.Seat;
import com.walmart.assignment.service.impl.CustomerServiceImpl;
import com.walmart.assignment.service.impl.LevelServiceImpl;
import com.walmart.assignment.service.impl.ReservationServiceImpl;
import com.walmart.assignment.service.impl.SeatServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class SeatServiceTest {

	@Resource(name="levelService")
	protected LevelServiceImpl levelService;

	@Resource(name="reservationService")
	protected ReservationServiceImpl reservationService;

	@Resource(name="customerService")
	protected CustomerServiceImpl customerService;

	@Resource(name="seatService")
	protected SeatServiceImpl seatService;
	
	@Test
	public void testGetAll() {
		List<Seat> seats = seatService.getAll();
		assertNotNull(seats);		
	}

	@Test
	public void testAdd() {
		Level level = levelService.add("Orchestra", new BigDecimal(100), new BigDecimal(25), new BigDecimal(50));
		Seat seat = seatService.add("OR100", "Available", level);
		assertNotNull(seat);
	}

	@Test
	public void testDelete() {
		Level level = levelService.add("Orchestra", new BigDecimal(100), new BigDecimal(25), new BigDecimal(50));
		Seat seat = seatService.add("OR100", "Available", level);
		seatService.delete(seat.getSeatId());
		seat = seatService.findById(seat.getSeatId());
		assertNull(seat);
	}

	@Test
	public void testEdit() {
		Level level1 = levelService.add("Orchestra", new BigDecimal(100), new BigDecimal(25), new BigDecimal(50));
		Seat seat = seatService.add("OR100", "Held", level1);
		Level level2 = levelService.add("Main", new BigDecimal(75), new BigDecimal(20), new BigDecimal(100));
		seatService.edit(seat.getSeatId(), seat.getSeatNumber(), "Available", level2);
		assertTrue(true);
	}

}
