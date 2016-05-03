package com.walmart.assignment.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.assignment.entity.Customer;
import com.walmart.assignment.entity.Reservation;

/**
 * Service for processing Reservations
 * 
 */
@Service("reservationService")
@Transactional
public class ReservationServiceImpl {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all reservations
	 * 
	 * @return a list of reservations
	 */
	public List<Reservation> getAll() {
		logger.debug("Retrieving all reservations");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Reservation");
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Adds a new reservation
	 * 
	 * @param firstName the first name of the reservation
	 * @param lastName the last name of the reservation
	 * @param money the money of the reservation
	 */
	public Reservation add(Customer customer) {
		logger.debug("Adding new reservation");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a new reservation
		Reservation reservation = new Reservation();
		reservation.setCustomerId(customer);
		reservation.setReservationTimeStamp(new Date());
		// Save
		session.save(reservation);
		return reservation;
	}
	
	/**
	 * Deletes an existing reservation
	 * @param id the id of the existing reservation
	 */
	public void delete(Long id) {
		logger.debug("Deleting existing reservation");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing reservation first
		Reservation reservation = (Reservation) session.get(Reservation.class, id);
		
		// Delete 
		session.delete(reservation);
	}
	
	/**
	 * Edits an existing reservation
	 * @param id the id of the existing reservation
	 * @param firstName the first name of the existing reservation
	 * @param lastName the last name of the existing reservation
	 * @param money the money of the existing reservation
	 */
	public void edit(Long id, Customer customerId, String confirmationCode) {
		logger.debug("Editing existing reservation");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing reservation via id
		Reservation reservation = (Reservation) session.get(Reservation.class, id);
		
		// Assign updated values to this reservation
		reservation.setCustomerId(customerId);
		reservation.setReservationTimeStamp(new Date());
		reservation.setConfirmationCode(confirmationCode);
		// Save updates
		session.save(reservation);
	}
	public Reservation findById(Long id) {
		logger.debug("Find existing Level");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing customer first
		Reservation r = (Reservation) session.get(Reservation.class, id);
		
		return r;
	}		
}
