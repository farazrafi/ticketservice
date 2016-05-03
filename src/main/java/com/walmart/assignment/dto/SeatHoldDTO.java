package com.walmart.assignment.dto;

import java.util.LinkedHashMap;

import com.walmart.assignment.entity.Seat;

public class SeatHoldDTO {
	
	private LinkedHashMap<Seat, String> selectedSeats =  new LinkedHashMap<Seat, String>();

	public LinkedHashMap<Seat, String> getSelectedSeats() {
		return selectedSeats;
	}

	public void setSelectedSeats(LinkedHashMap<Seat, String> selectedSeats) {
		this.selectedSeats = selectedSeats;
	}
}