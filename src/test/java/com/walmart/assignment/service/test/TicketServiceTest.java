package com.walmart.assignment.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.assignment.entity.Level;
import com.walmart.assignment.entity.Seat;
import com.walmart.assignment.entity.SeatHold;
import com.walmart.assignment.service.TicketService;
import com.walmart.assignment.service.impl.CustomerServiceImpl;
import com.walmart.assignment.service.impl.LevelServiceImpl;
import com.walmart.assignment.service.impl.ReservationServiceImpl;
import com.walmart.assignment.service.impl.SeatHoldServiceImpl;
import com.walmart.assignment.service.impl.SeatServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class TicketServiceTest {

	@Resource(name="customerService")
	protected CustomerServiceImpl customerService;
	
	@Resource(name="levelService")
	protected LevelServiceImpl levelService;

	@Resource(name="reservationService")
	protected ReservationServiceImpl reservationService;

	@Resource(name="seatService")
	protected SeatServiceImpl seatService;

	@Resource(name="seatHoldService")
	protected SeatHoldServiceImpl seatHoldService;
	
	@Resource(name="ticketService")
	protected TicketService ticketService;

	static boolean initializer = false;
	static int numSeats = 5;
	static Integer minLevel = 3;
	static Integer maxLevel = 1;
	static Integer availableSeats = new Integer(0);
	static String customerEmail = "faraz@abc.com";
	static List<Seat> minseats = new ArrayList<Seat>();
	static List<Seat> maxseats = new ArrayList<Seat>();
	static SeatHold seatHold = new SeatHold();
	
	@Test
	public void useCaseOne(){
		//OVERVIEW: FIND THE NUMBER OF SEATS AVAILABLE WITHIN THE VENUE, OPTIONALLY BY SEATING LEVEL
		List<Integer> venueLevels = new ArrayList<Integer>();
		venueLevels.add(1);//Orchestra
		venueLevels.add(2);//Main
		availableSeats = ticketService.numSeatsAvailable(venueLevels);
		System.out.println("available seats for " + venueLevels + ": " + availableSeats);
		System.out.println("----------------------------------------------");
		System.out.println("USE CASE # 1: Available Seats: " + availableSeats);
		System.out.println("----------------------------------------------");
		assertNotNull(availableSeats);
		initializer = true;
	}
	@Test
	public void useCaseTwo(){
		this.useCaseOne();
		if(availableSeats>0){
    		seatHold = ticketService.findAndHoldSeats(numSeats, new Integer(minLevel), new Integer(maxLevel), customerEmail);
				minseats = seatService.getSeatsByLevel(minLevel);
				maxseats = seatService.getSeatsByLevel(maxLevel);
			Integer seatHoldCount = 0;
			for(Seat seat : maxseats){
				seat.setHeld("Held");
				seat.setSeatHoldId(seatHold);
				seatService.edit(seat.getSeatId(), seat.getSeatNumber(), "Held", seat.getLevelId());
				seatHoldCount++;
				if(numSeats==seatHoldCount){
					break;
				}
			}
			if(seatHoldCount<numSeats){
				for(Seat seat : minseats){
					seat.setHeld("Held");
					seat.setSeatHoldId(seatHold);
					seatService.edit(seat.getSeatId(), seat.getSeatNumber(), "Held", seat.getLevelId());
					seatHoldCount++;
					if(numSeats==seatHoldCount){
						break;
					}
				}				
			}
		}
		System.out.println("----------------------------------------------");
		System.out.println("USE CASE # 2: Seat Hold ID: " + seatHold.getSeatHoldId());
		System.out.println("----------------------------------------------");
		assertNotNull(seatHold);
	}
	@Test
	public void useCaseThree(){
		//OVERVIEW: RESERVE AND COMMIT A SPECIFIC GROUP OF SEATS FOR A CUSTOMER
		this.useCaseTwo();
		String confirmationNumber = ticketService.reserveSeats(seatHold.getSeatHoldId().intValue(), "farazcis@yahoo.com");
		System.out.println("----------------------------------------------");
		System.out.println("USE CASE # 3: CONFIRMATION: " + confirmationNumber);
		System.out.println("----------------------------------------------");
		assertNotNull(confirmationNumber);
	}
	@Before
	public void initializeTestData(){
		if(!initializer){
			customerService.add("Faraz", "Rafi", "test1@email.com");
			customerService.add("John", "Callahan", "test2@email.com");
			customerService.add("Patrick", "Elias", "test3@email.com");
			
			levelService.add("Orchestra", new BigDecimal(100), new BigDecimal(25), new BigDecimal(50));
			levelService.add("Main", new BigDecimal(75), new BigDecimal(20), new BigDecimal(100));
			levelService.add("Balcony 1", new BigDecimal(50), new BigDecimal(15), new BigDecimal(100));
			levelService.add("Balcony 2", new BigDecimal(40), new BigDecimal(15), new BigDecimal(100));

			List<Level> levels = levelService.getAll();
			for(Level level : levels){
				if(level.getLevelName().equalsIgnoreCase("Orchestra")){
					for(int row = 0 ; row < 25 ; row++){
						for(int seat = 0 ; seat < 50 ; seat++){
							seatService.add("OR"+(row+1)+(seat+1), "Available", level);
						}
					}
				}else if(level.getLevelName().equalsIgnoreCase("Main")){
					for(int row = 0 ; row < 20 ; row++){
						for(int seat = 0 ; seat < 100 ; seat++){
							seatService.add("MN"+(row+1)+(seat+1), "Available", level);
						}
					}
				}else if(level.getLevelName().equalsIgnoreCase("Balcony 1")){
					for(int row = 0 ; row < 15 ; row++){
						for(int seat = 0 ; seat < 100 ; seat++){
							seatService.add("B1"+(row+1)+(seat+1), "Available", level);
						}
					}
				}else if(level.getLevelName().equalsIgnoreCase("Balcony 2")){
					for(int row = 0 ; row < 15 ; row++){
						for(int seat = 0 ; seat < 100 ; seat++){
							seatService.add("B2"+(row+1)+(seat+1), "Available", level);
						}
					}
				}
			}
		}
		assertTrue(true);
	}	
}
