package com.walmart.assignment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.assignment.entity.Reservation;
import com.walmart.assignment.entity.SeatHold;

@Service("seatHoldService")
@Transactional
public class SeatHoldServiceImpl {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all persons
	 * 
	 * @return a list of persons
	 */
	public List<SeatHold> getAll() {
		logger.debug("Retrieving all seatHolds");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  SeatHold");
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Adds a new seatHold
	 * 
	 * @param firstName the first name of the seatHold
	 * @param lastName the last name of the seatHold
	 * @param money the money of the seatHold
	 * @return 
	 */
	public SeatHold add(Reservation reservation, String hold) {
		logger.debug("Adding new seatHold");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a new seatHold
		SeatHold seatHold = new SeatHold();
		seatHold.setHold(hold);
//		seatHold.setLevelId(level);
		seatHold.setReservationId(reservation);
//		seatHold.setSeatId(seat);
		// Save
		session.save(seatHold);
		return seatHold;
	}
	
	/**
	 * Deletes an existing seatHold
	 * @param id the id of the existing seatHold
	 */
	public void delete(Long id) {
		logger.debug("Deleting existing seatHold");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing seatHold first
		SeatHold seatHold = (SeatHold) session.get(SeatHold.class, id);
		
		// Delete 
		session.delete(seatHold);
	}
	
	/**
	 * Edits an existing seatHold
	 * @param id the id of the existing seatHold
	 * @param firstName the first name of the existing seatHold
	 * @param lastName the last name of the existing seatHold
	 * @param money the money of the existing seatHold
	 */
	public void edit(Long id, Reservation reservation, String hold) {
		logger.debug("Editing existing seatHold");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing seatHold via id
		SeatHold seatHold = (SeatHold) session.get(SeatHold.class, id);
		
		// Assign updated values to this seatHold
		seatHold.setHold(hold);
		seatHold.setReservationId(reservation);

		// Save updates
		session.save(seatHold);
	}
	public SeatHold findById(Long id) {
		logger.debug("Find existing Level");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing customer first
		SeatHold s = (SeatHold) session.get(SeatHold.class, id);
		
		return s;
	}		
}
