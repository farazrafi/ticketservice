package com.walmart.assignment.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.assignment.entity.Level;

/**
 * Service for processing Levels
 * 
 */
@Service("levelService")
@Transactional
public class LevelServiceImpl {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all levels
	 * 
	 * @return a list of levels
	 */
	public List<Level> getAll() {
		logger.debug("Retrieving all levels");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Level");
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Adds a new level
	 * 
	 * @param firstName the first name of the level
	 * @param lastName the last name of the level
	 * @param money the money of the level
	 * @return 
	 */
	public Level add(String levelName, BigDecimal price, BigDecimal numberOfRows, BigDecimal seatsInRow) {
		logger.debug("Adding new level");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a new level
		Level level = new Level();
		level.setLevelName(levelName);
		level.setPrice(price);
		level.setNumberOfRows(numberOfRows);
		level.setSeatsInRow(seatsInRow);	
		// Save
		session.save(level);
		return level;
	}
	
	/**
	 * Deletes an existing level
	 * @param id the id of the existing level
	 */
	public void delete(Long id) {
		logger.debug("Deleting existing level");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing level first
		Level level = (Level) session.get(Level.class, id);
		
		// Delete 
		session.delete(level);
	}
	
	/**
	 * Edits an existing level
	 * @param id the id of the existing level
	 * @param firstName the first name of the existing level
	 * @param lastName the last name of the existing level
	 * @param money the money of the existing level
	 */
	public void edit(Long id, String levelName, BigDecimal price, BigDecimal numberOfRows, BigDecimal seatsInRow) {
		logger.debug("Editing existing level");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing level via id
		Level level = (Level) session.get(Level.class, id);
		
		// Assign updated values to this level
		level.setLevelName(levelName);
		level.setPrice(price);
		level.setNumberOfRows(numberOfRows);
		level.setSeatsInRow(seatsInRow);

		// Save updates
		session.save(level);
	}

	public Level findById(Long id) {
		logger.debug("Find existing Level");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing customer first
		Level level = (Level) session.get(Level.class, id);
		
		return level;
	}	
	
}
