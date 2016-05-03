package com.walmart.assignment.entity;

import java.util.Date;
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
@Table(name = "reservation")
public class Reservation implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long reservationId;
//	private Event event;
	private Customer customerId;
//	private SeatHold seatHoldId;
	private Date reservationTimeStamp;
	private String confirmationCode;
	private Set<SeatHold> seatHolds = new HashSet<SeatHold>();

	@Id
	@Column(name = "reservationId")
	@GeneratedValue
	public Long getReservationId() { return reservationId; }
	public void setReservationId(Long reservationId) { this.reservationId = reservationId; }

	@Column(name = "RESERVATION_TIME")
	public Date getReservationTimeStamp() { return reservationTimeStamp; }
	public void setReservationTimeStamp(Date reservationTimeStamp) { this.reservationTimeStamp = reservationTimeStamp; }

	@Column(name = "CONFIRMATION_CODE")
	public String getConfirmationCode() { return confirmationCode; }
	public void setConfirmationCode(String confirmationCode) { this.confirmationCode = confirmationCode; }

    @ManyToOne
    @JoinColumn(name = "customerId")
	public Customer getCustomerId() { return customerId; }
	public void setCustomerId(Customer customerId) { this.customerId = customerId; }
	
//	@Column(name = "EVENT_ID")
//	public Event getEvent() { return event; }
//	public void setEvent(Event event) { this.event = event; }

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="reservationId")
	public Set<SeatHold> getSeatHolds() { return seatHolds; }
	public void setSeatHolds(Set<SeatHold> seatHolds) { this.seatHolds = seatHolds; }	
}