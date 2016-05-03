package com.walmart.assignment.entity;

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
@Table(name = "customer")
public class Customer implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long customerId; 
	private String firstName;
	private String lastName;
	private String email;
	private Set<Reservation> reservations = new HashSet<Reservation>();

	@Id
	@Column(name = "customerId")
	@GeneratedValue
	public Long getCustomerId() { return customerId; }
	public void setCustomerId(Long customerId) { this.customerId = customerId; }

	@Column(name = "FIRST_NAME")
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	@Column(name = "LAST_NAME")
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	@Column(name = "EMAIL")
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="customerId")
	public Set<Reservation> getReservations() { return reservations; }
	public void setReservations(Set<Reservation> reservations) { this.reservations = reservations; }	
}