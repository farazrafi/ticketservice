package com.walmart.assignment.service;

import java.util.List;

import com.walmart.assignment.entity.SeatHold;

public interface TicketService {
	int numSeatsAvailable(List<Integer> venueLevel);
	SeatHold findAndHoldSeats(int numSeats, Integer minLevel, Integer maxLevel, String customerEmail);
	String reserveSeats(int seatHoldId, String customerEmail);
}
