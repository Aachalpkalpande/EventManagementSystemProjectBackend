package com.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.model.Event;

public interface EventService {
	 // Fetch all events without pagination
	List<Event> getAllEvents(); 
	
	 
	
    // Get a single event by its ID
	Event getEventById(Long id);
	
	 // Create and save a new event
	Event createEvent(Event event);
	
	
	  // Update an existing event using ID
	Event updateEvent(Long id, Event eventDetails);
	
	 // Delete an event by ID
	void deleteEvent(Long id); 
	

    // Search events by title with pagination
    Page<Event> searchEventsByTitle(String keyword, Pageable pageable);

    // Filter events by category ID with pagination
    Page<Event> filterByCategoryId(Long categoryId, Pageable pageable);

    // Filter events by venue ID with pagination
    Page<Event> filterByVenueId(Long venueId, Pageable pageable);

}