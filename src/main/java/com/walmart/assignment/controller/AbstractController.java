package com.walmart.assignment.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.walmart.assignment.entity.Level;
import com.walmart.assignment.service.TicketService;
import com.walmart.assignment.service.impl.CustomerServiceImpl;
import com.walmart.assignment.service.impl.LevelServiceImpl;
import com.walmart.assignment.service.impl.ReservationServiceImpl;
import com.walmart.assignment.service.impl.SeatHoldServiceImpl;
import com.walmart.assignment.service.impl.SeatServiceImpl;

@Controller
@SessionAttributes({"searchDTO", "seatHoldDTO", "seatHold", "reservation"})
public abstract class AbstractController {
	
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

    protected void initializeCustomers(){
		customerService.add("Faraz", "Rafi", "test@email.com");
		customerService.add("John", "Callahan", "test@email.com");
		customerService.add("Patrick", "Elias", "test@email.com");
		
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
    
}