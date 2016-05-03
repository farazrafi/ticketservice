package com.walmart.assignment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * For a complete reference see 
 * <a href="http://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/">
 * Hibernate Annotations Communit Documentations</a>
 */
@Entity
@Table(name = "SEAT")
public class Seat implements Serializable {

	private static final long serialVersionUID = -5527566248002296042L;
	
	private Long seatId;
	private String seatNumber;
	private String held;
	private Level levelId;
	private SeatHold seatHoldId;
//	private Set<SeatHold> seatHolds = new HashSet<SeatHold>();

	@Id
	@Column(name = "seatId")
	@GeneratedValue
	public Long getSeatId() {
		return seatId;
	}
	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}
	@Column(name = "SEAT_NUMBER")
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	@Column(name = "HELD")
	public String getHeld() {
		return held;
	}
	public void setHeld(String held) {
		this.held = held;
	}
    @ManyToOne
	@JoinColumn(name = "levelId")
	public Level getLevelId() {
		return levelId;
	}
	public void setLevelId(Level levelId) {
		this.levelId = levelId;
	}
    @ManyToOne
    @JoinColumn(name = "seatHoldId")
	public SeatHold getSeatHoldId() {
		return seatHoldId;
	}
	public void setSeatHoldId(SeatHold seatHoldId) {
		this.seatHoldId = seatHoldId;
	}
	
//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="seatId")
//	public Set<SeatHold> getSeatHolds() { return seatHolds; }
//	public void setSeatHolds(Set<SeatHold> seatHolds) { this.seatHolds = seatHolds; }	
}