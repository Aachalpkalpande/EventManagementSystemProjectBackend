package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.VenueRepository;
import com.exceptiondemo.VenueNotFoundException;
import com.model.Venue;

@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    private VenueRepository venueRepo;
    
    public List<Venue> getAllVenues() {
        return venueRepo.findAll();
    }
/*
    @Override
    public Page<Venue> getAllVenues(Pageable pageable) {
        return venueRepo.findAll(pageable);
    }
*/
    @Override
    public Optional<Venue> getVenueById(Long id) {
        return venueRepo.findById(id);
    }

    @Override
    public Venue createVenue(Venue venue) {
        // Validate venue data before saving if needed (e.g., check if a venue with same name exists)
        return venueRepo.save(venue);
    }

    @Override
    public Venue updateVenue(Long id, Venue venueDetails) {
        Venue existingVenue = venueRepo.findById(id)
                .orElseThrow(() -> new VenueNotFoundException("Venue with ID " + id + " not found"));

        // Update venue details
        existingVenue.setName(venueDetails.getName());
        existingVenue.setLocation(venueDetails.getLocation());

        return venueRepo.save(existingVenue);
    }

    @Override
    public void deleteVenue(Long id) {
        Venue existingVenue = venueRepo.findById(id)
                .orElseThrow(() -> new VenueNotFoundException("Venue with ID " + id + " not found"));

        venueRepo.delete(existingVenue);
    }

    @Override
    public List<Venue> searchByName(String name) {
        return venueRepo.findByNameContainingIgnoreCase(name);
    }
    
    @Override
    public List<Venue> filterByLocation(String location) {
        return venueRepo.findByLocationContainingIgnoreCase(location);
    }
    
}