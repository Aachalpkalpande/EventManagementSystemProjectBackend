package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long>{
	 // Custom query to search venues by name (case-insensitive)
    List<Venue> findByNameContainingIgnoreCase(String name);

    // Custom query to filter venues by location (case-insensitive)
    List<Venue> findByLocationContainingIgnoreCase(String location);
}