package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	 // Search by event title (case-insensitive)
    Page<Event> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    // Filter by category ID
    Page<Event> findByCategoryId(Long categoryId, Pageable pageable);

	Page<Event> findByVenueId(Long venueId, Pageable pageable);
}