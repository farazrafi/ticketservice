package com.walmart.assignment.dto;

public class SearchDTO {
	private String venueLevel;
	private Integer numSeats;
	private String minLevel;
	private String maxLevel;
	private String firstName;
	private String lastName;
	private String customerEmail;
	public String getVenueLevel() {
		return venueLevel;
	}
	public void setVenueLevel(String venueLevel) {
		this.venueLevel = venueLevel;
	}
	public Integer getNumSeats() {
		return numSeats;
	}
	public void setNumSeats(Integer numSeats) {
		this.numSeats = numSeats;
	}
	public String getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(String minLevel) {
		this.minLevel = minLevel;
	}
	public String getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(String maxLevel) {
		this.maxLevel = maxLevel;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}