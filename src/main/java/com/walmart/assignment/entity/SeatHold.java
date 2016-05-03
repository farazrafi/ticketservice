package com.walmart.assignment.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SEAT_HOLD")
public class SeatHold implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long seatHoldId; 
	private String hold;
//	private Seat seatId;
//	private Level levelId;
	private Reservation reservationId;
	private Set<Seat> seats = new HashSet<Seat>();

	@Id
	@Column(name = "seatHoldId")
	@GeneratedValue
	public Long getSeatHoldId() { return seatHoldId; }
	public void setSeatHoldId(Long seatHoldId) { this.seatHoldId = seatHoldId; }
	
//	@ManyToOne
//	@JoinColumn(name = "seatId")
//	public Seat getSeatId() { return seatId; }
//	public void setSeatId(Seat seatId) { this.seatId = seatId; }

	@Column(name = "HOLD")
	public String getHold() { return hold; }
	public void setHold(String hold) { this.hold = hold; }

//	@ManyToOne
//	@JoinColumn(name = "levelId")
//	public Level getLevelId() { return levelId; }
//	public void setLevelId(Level levelId) { this.levelId = levelId; }	
	
	@ManyToOne
	@JoinColumn(name = "reservationId")
	public Reservation getReservationId() { return reservationId; }
	public void setReservationId(Reservation reservationId) { this.reservationId = reservationId; }	

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="seatHoldId")
	public Set<Seat> getSeats() { return seats; }
	public void setSeats(Set<Seat> seats) { this.seats = seats; }	
}