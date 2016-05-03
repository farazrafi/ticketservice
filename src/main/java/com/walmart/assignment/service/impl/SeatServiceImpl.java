package com.walmart.assignment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.assignment.entity.Level;
import com.walmart.assignment.entity.Seat;

@Service("seatService")
@Transactional
public class SeatServiceImpl {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all persons
	 * 
	 * @return a list of persons
	 */
	public List<Seat> getAll() {
		logger.debug("Retrieving all seats");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Seat");
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Adds a new seat
	 * 
	 * @param firstName the first name of the seat
	 * @param lastName the last name of the seat
	 * @param money the money of the seat
	 * @return 
	 */
	public Seat add(String seatNumber, String held, Level level) {
		logger.debug("Initializing TEST DATA: new seat "+ seatNumber + " on level " + level.getLevelName());
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a new seat
		Seat seat = new Seat();
		seat.setSeatNumber(seatNumber);
		seat.setHeld(held);
		seat.setLevelId(level);
		// Save
		session.save(seat);
		return seat;
	}
	
	/**
	 * Deletes an existing seat
	 * @param id the id of the existing seat
	 */
	public void delete(Long id) {
		logger.debug("Deleting existing seat");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing seat first
		Seat seat = (Seat) session.get(Seat.class, id);
		
		// Delete 
		session.delete(seat);
	}
	
	/**
	 * Edits an existing seat
	 * @param id the id of the existing seat
	 * @param firstName the first name of the existing seat
	 * @param lastName the last name of the existing seat
	 * @param money the money of the existing seat
	 */
	public void edit(Long id, String seatNumber, String held, Level level) {
		logger.debug("Editing existing seat");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing seat via id
		Seat seat = (Seat) session.get(Seat.class, id);
		
		// Assign updated values to this seat
		seat.setSeatNumber(seatNumber);
		seat.setHeld(held);
		seat.setLevelId(level);

		// Save updates
		session.save(seat);
	}
	
	@SuppressWarnings("unchecked")
	public List<Seat> getSeatsByLevel(Integer levelId){
		logger.debug("Retrieving all seats by level " + levelId);
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Seat model where model.levelId.levelId = " + levelId +""
										 + " and model.held = 'Available'" );
		
		// Retrieve all
		return  query.list();
	}
	public Seat findById(Long id) {
		logger.debug("Find existing Seat");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing customer first
		Seat s = (Seat) session.get(Seat.class, id);
		
		return s;
	}		
}