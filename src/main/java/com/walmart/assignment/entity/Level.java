package com.walmart.assignment.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LEVEL")
public class Level implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long levelId; 
	private String levelName;
	private BigDecimal price;
	private BigDecimal numberOfRows;
	private BigDecimal seatsInRow;
	private Set<Seat> seats = new HashSet<Seat>();
//	private Set<SeatHold> seatHolds = new HashSet<SeatHold>();

	@Id
	@Column(name = "levelId")
	@GeneratedValue
	public Long getLevelId() { return levelId; }
	public void setLevelId(Long levelId) { this.levelId = levelId; }

	@Column(name = "LEVEL_NAME")
	public String getLevelName() { return levelName; }
	public void setLevelName(String levelName) { this.levelName = levelName; }

	@Column(name = "PRICE")
	public BigDecimal getPrice() { return price; }
	public void setPrice(BigDecimal price) { this.price = price; }

	@Column(name = "NO_OF_ROWS")
	public BigDecimal getNumberOfRows() { return numberOfRows; }
	public void setNumberOfRows(BigDecimal numberOfRows) { this.numberOfRows = numberOfRows; }	
	
	@Column(name = "SEATS_IN_ROW")
	public BigDecimal getSeatsInRow() { return seatsInRow; }
	public void setSeatsInRow(BigDecimal seatsInRow) { this.seatsInRow = seatsInRow; }

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="levelId")	
	public Set<Seat> getSeats() { return seats; }
	public void setSeats(Set<Seat> seats) { this.seats = seats;}
	
//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="levelId")
//	public Set<SeatHold> getSeatHolds() { return seatHolds; }
//	public void setSeatHolds(Set<SeatHold> seatHolds) { this.seatHolds = seatHolds; }	
}