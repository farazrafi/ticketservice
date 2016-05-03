package com.walmart.assignment.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.assignment.entity.Customer;
import com.walmart.assignment.entity.Reservation;
import com.walmart.assignment.entity.Seat;
import com.walmart.assignment.entity.SeatHold;
import com.walmart.assignment.service.TicketService;

@Service("ticketService")
@Transactional
public class TicketServiceImpl implements TicketService {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;	

	@Resource(name="seatHoldService")
	protected SeatHoldServiceImpl seatHoldService;

	@Resource(name="seatService")
	protected SeatServiceImpl seatService;

	@Resource(name="reservationService")
	protected ReservationServiceImpl reservationService;

	@Resource(name="customerService")
	protected CustomerServiceImpl customerService;
	
	@SuppressWarnings("unchecked")
	public int numSeatsAvailable(List<Integer> venueLevel) {
		Session session = sessionFactory.getCurrentSession();
		String operand = "";
		int count = 0;
		for(Integer i : venueLevel){			
			operand += i + (count<venueLevel.size()-1 ? ", " : "") ;
			count++;
		}
		Query query = session.createQuery(" from Seat model where model.levelId.levelId in ("+operand+") and model.held = 'Available' ");
		List<Seat> seats = query.list();		
		return seats.size();
	}

	public SeatHold findAndHoldSeats(int numSeats, Integer minLevel, Integer maxLevel, String customerEmail) {
		SeatHold seatHold = new SeatHold();
    	Customer customer = null;
    	if(StringUtils.isNotEmpty(customerEmail)){
    		customer = customerService.add("Generic", "User", customerEmail);
    	}
    	Reservation reservation = reservationService.add(customer);
    	seatHold = seatHoldService.add(reservation, "Held");
		return seatHold;
	}

	@SuppressWarnings("unchecked")
	public String reserveSeats(int seatHoldId, String customerEmail) {
		Session session1 = sessionFactory.getCurrentSession();
		Query query1 = session1.createQuery(" from SeatHold model where model.seatHoldId = " + seatHoldId);
		SeatHold seatHold = (SeatHold) query1.uniqueResult();
		Reservation reservation = seatHold.getReservationId();
		String reservationConfirmationCode = "CONFIRMED"+new Date();
		reservationService.edit(reservation.getReservationId(), reservation.getCustomerId(), reservationConfirmationCode);
		seatHold.setHold("Reserved");
		seatHoldService.edit(seatHold.getSeatHoldId(), reservation, "Reserved");
		
		Session session2 = sessionFactory.getCurrentSession();
		Query query2 = session2.createQuery(" from Seat where seatHoldId.seatHoldId = " + new Long(seatHoldId));
		List<Seat> seats = query2.list();
		for(Seat seat : seats){
			seatService.edit(seat.getSeatId(), seat.getSeatNumber(), "Reserved", seat.getLevelId());
		}
		return reservationConfirmationCode;
	}

}
