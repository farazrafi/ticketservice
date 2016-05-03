package com.walmart.assignment.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.walmart.assignment.dto.SearchDTO;
import com.walmart.assignment.dto.SeatHoldDTO;
import com.walmart.assignment.entity.Customer;
import com.walmart.assignment.entity.Level;
import com.walmart.assignment.entity.Reservation;
import com.walmart.assignment.entity.Seat;
import com.walmart.assignment.entity.SeatHold;


/**
 * Handles and retrieves person request
 */
@Controller
public class MainController extends AbstractController{

	protected static Logger logger = Logger.getLogger("controller");
	
	@ModelAttribute("searchDTO")
	public final SearchDTO initSortingDTO(HttpSession session) {
		SearchDTO searchDTO = null;
		Object value = session.getAttribute("searchDTO");
		searchDTO = (value == null) ? new SearchDTO() : (SearchDTO)value;
		return searchDTO;
	}
	@ModelAttribute("seatHoldDTO")
	public final SeatHoldDTO initSeatHoldDTO(HttpSession session) {
		SeatHoldDTO seatHoldDTO = null;
		Object value = session.getAttribute("seatHoldDTO");
		seatHoldDTO = (value == null) ? new SeatHoldDTO() : (SeatHoldDTO)value;
		return seatHoldDTO;
	}	
	@ModelAttribute("seatHold")
	public final SeatHold initSeatHold(HttpSession session) {
		SeatHold seatHold = null;
		Object value = session.getAttribute("seatHold");
		seatHold = (value == null) ? new SeatHold() : (SeatHold)value;
		return seatHold;
	}	
	@ModelAttribute("reservation")
	public final Reservation initReservation(HttpSession session) {
		Reservation reservation = null;
		Object value = session.getAttribute("reservation");
		reservation = (value == null) ? new Reservation() : (Reservation)value;
		return reservation;
	}	
	/**
	 * Handles and retrieves all persons and show it in a JSP page
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/testdata", method = RequestMethod.GET)
    public String getTestData(Model model) {
    	
    	logger.debug("Retrieving all test data");
    	
    	// Retrieve all persons by delegating the call to PersonService
    	List<Customer> customers = customerService.getAll();
    	List<Level> levels = levelService.getAll();
    	List<Reservation> reservations = reservationService.getAll();    	
    	List<Seat> seats = seatService.getAll();
    	List<SeatHold> seatHolds = seatHoldService.getAll();    	    	
    	
    	model.addAttribute("seats", seats);
    	model.addAttribute("levels", levels);
    	model.addAttribute("seatHolds", seatHolds);
    	model.addAttribute("customers", customers);
    	model.addAttribute("reservations", reservations);
    	return "testdata";
	}
	
    @RequestMapping(value = "/numseatsavailable", method = RequestMethod.GET)
    public String getSeatSearch(Model model, 
    		@ModelAttribute("searchDTO") SearchDTO searchDTO, 
    		@ModelAttribute("seatHoldDTO") SeatHoldDTO seatHoldDTO
    		) {
    	
    	logger.debug("Inside GET numseatsavailable");    	
    	
    	model.addAttribute("searchDTO", searchDTO);
    	return "numseatsavailable";
	}
    @RequestMapping(value = "/numseatsavailable", method = RequestMethod.POST)
    public String postSeatSearch(Model model, @ModelAttribute("searchDTO") SearchDTO searchDTO,
    		@ModelAttribute("seatHoldDTO") SeatHoldDTO seatHoldDTO
    		) {
    	
    	logger.debug("Inside POST numseatsavailable");    	
		List<Integer> venueLevels = new ArrayList<Integer>();    	
    	if(StringUtils.isNotEmpty(searchDTO.getVenueLevel())){
    		String[] keys = searchDTO.getVenueLevel().split(",");
    		for(String key : keys){
    			venueLevels.add(new Integer(key));
    		}    		
    	}
    	model.addAttribute("result", ticketService.numSeatsAvailable(venueLevels));
    	model.addAttribute("searchDTO", searchDTO);
    	return "numseatsavailable";
	}

    @RequestMapping(value = "/findAndHoldSeats", method = RequestMethod.GET)
    public String getFindAndHoldSeats(Model model, @ModelAttribute("searchDTO") SearchDTO searchDTO,
    		@ModelAttribute("seatHoldDTO") SeatHoldDTO seatHoldDTO
    		) {
    	
    	logger.debug("Inside GET findAndHoldSeats");    	
    	model.addAttribute("seatHoldDTO", seatHoldDTO);
    	model.addAttribute("searchDTO", searchDTO);
    	return "findAndHoldSeats";
	}
    @RequestMapping(value = "/findAndHoldSeats", method = RequestMethod.POST)
    public String postFindAndHoldSeats(Model model, @ModelAttribute("searchDTO") SearchDTO searchDTO,
    		@RequestParam(value="userAction", required=false) String userAction,
    		@ModelAttribute("seatHoldDTO") SeatHoldDTO seatHoldDTO) {    	
    	logger.debug("Inside POST findAndHoldSeats");
    	int totalSeatsAvailable = 0;
    	List<Seat> minseats = new ArrayList<Seat>();
    	List<Seat> maxseats = new ArrayList<Seat>();
    	if("search".equalsIgnoreCase(userAction)){
        	Reservation reservation = new Reservation();
        	SeatHold seatHold = new SeatHold();
        	model.addAttribute("seatHold" , seatHold);
        	model.addAttribute("reservation" , reservation);
    		seatHoldDTO.setSelectedSeats(new LinkedHashMap<Seat, String>());
        	if(StringUtils.isNotEmpty(searchDTO.getMinLevel()) && StringUtils.isNotEmpty(searchDTO.getMaxLevel())){
        		minseats = seatService.getSeatsByLevel(new Integer(searchDTO.getMinLevel()));
        		maxseats = seatService.getSeatsByLevel(new Integer(searchDTO.getMaxLevel()));
        		if(!searchDTO.getMinLevel().equalsIgnoreCase(searchDTO.getMaxLevel())){
                	totalSeatsAvailable = minseats.size() + maxseats.size();
        		}else{
        			totalSeatsAvailable = minseats.size();
        		}
        	}
    	}else if("hold".equalsIgnoreCase(userAction)){
    		SeatHold seatHold = ticketService.findAndHoldSeats(searchDTO.getNumSeats().intValue(), new Integer(searchDTO.getMinLevel()), 
    				 										   new Integer(searchDTO.getMaxLevel()), searchDTO.getCustomerEmail());
        	model.addAttribute("seatHold" , seatHold);
        	model.addAttribute("reservation" , seatHold.getReservationId());
        	if(StringUtils.isNotEmpty(searchDTO.getMinLevel()) && StringUtils.isNotEmpty(searchDTO.getMaxLevel())){
        		minseats = seatService.getSeatsByLevel(new Integer(searchDTO.getMinLevel()));
        		maxseats = seatService.getSeatsByLevel(new Integer(searchDTO.getMaxLevel()));
        		if(!searchDTO.getMinLevel().equalsIgnoreCase(searchDTO.getMaxLevel())){
                	totalSeatsAvailable = minseats.size() + maxseats.size();
        		}else{
        			totalSeatsAvailable = minseats.size();
        		}
            	Integer seatHoldCount = 0;
            	for(Seat seat : maxseats){
            		seatHoldDTO.getSelectedSeats().put(seat, "Held");
            		seat.setHeld("Held");
            		seat.setSeatHoldId(seatHold);
            		seatService.edit(seat.getSeatId(), seat.getSeatNumber(), "Held", seat.getLevelId());
            		seatHoldCount++;
            		if(searchDTO.getNumSeats().equals(seatHoldCount)){
            			break;
            		}
            	}
            	if(seatHoldCount<searchDTO.getNumSeats()){
                	for(Seat seat : minseats){
                		seatHoldDTO.getSelectedSeats().put(seat, "Held");
                		seat.setHeld("Held");
                		seat.setSeatHoldId(seatHold);
                		seatService.edit(seat.getSeatId(), seat.getSeatNumber(), "Held", seat.getLevelId());
                		seatHoldCount++;
                		if(searchDTO.getNumSeats().equals(seatHoldCount)){
                			break;
                		}
                	}
            	}
        	}
    	}
    	model.addAttribute("searchDTO", searchDTO);
    	model.addAttribute("seatHoldDTO", seatHoldDTO);
    	model.addAttribute("totalSeatsAvailable" , totalSeatsAvailable);
    	return "findAndHoldSeats";
	}  

    @RequestMapping(value = "/reserve", method = RequestMethod.GET)
    public String getReserve(Model model, @ModelAttribute("searchDTO") SearchDTO searchDTO,
    		@ModelAttribute("seatHoldDTO") SeatHoldDTO seatHoldDTO
    		) {    	
    	logger.debug("Inside GET reserve");    	
    	//make reservation
    	//hold seats
    	model.addAttribute("seatHoldDTO", seatHoldDTO);
    	model.addAttribute("searchDTO", searchDTO);
    	return "reserve";
	}
    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public String postReserve(Model model, @ModelAttribute("searchDTO") SearchDTO searchDTO,
    		@ModelAttribute("seatHoldDTO") SeatHoldDTO seatHoldDTO,
    		@ModelAttribute("reservation") Reservation reservation,
    		@ModelAttribute("seatHold") SeatHold seatHold
    		) {    	
    	logger.debug("Inside POST reserve");
    	
    	if(StringUtils.isNotEmpty(searchDTO.getCustomerEmail())){    		
        	String confirmationCode = ticketService.reserveSeats(seatHold.getSeatHoldId().intValue(), searchDTO.getCustomerEmail());
        	model.addAttribute("confirmationCode", confirmationCode);
    	}
    	model.addAttribute("searchDTO", searchDTO);
    	return "reserve";
	}    

    @RequestMapping(value = "/initialize", method = RequestMethod.GET)
    public String getInitPage(Model model) {
    	
    	logger.debug("Received request to show all persons");
    	
    	// Retrieve all persons by delegating the call to PersonService
    	List<Customer> customers = customerService.getAll();
    	List<Level> levels = levelService.getAll();
    	List<Reservation> reservations = reservationService.getAll();
    	
    	
    	model.addAttribute("customers", customers);
    	model.addAttribute("levels", levels);
    	model.addAttribute("reservations", reservations);
    	return "initialize";
	}
    @RequestMapping(value = "/initialize.do", method = RequestMethod.POST)
    public final String initializeTestData(Model model,
    		@ModelAttribute("customers") ArrayList<Customer> customers){
    	logger.info("POST Initialization");
    	String redirect = "redirect:initialize";
    	this.initializeCustomers();
    	return redirect;
    }
}
