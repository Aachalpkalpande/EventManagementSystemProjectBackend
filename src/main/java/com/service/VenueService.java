package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.model.Venue;

public interface VenueService {

	public List<Venue> getAllVenues();
	
	// Get all venues with pagination
//    Page<Venue> getAllVenues(Pageable pageable); 

    // Get a venue by ID, return Optional to allow better error handling
    Optional<Venue> getVenueById(Long id); 

    // Create a new venue
    Venue createVenue(Venue venue);

    // Update venue by ID
    Venue updateVenue(Long id, Venue venueDetails); 

    // Delete venue by ID
    void deleteVenue(Long id);

    // Search venues by name (case-insensitive)
    List<Venue> searchByName(String name);

    // Filter venues by location (you can add more filters)
    List<Venue> filterByLocation(String location);
   
}