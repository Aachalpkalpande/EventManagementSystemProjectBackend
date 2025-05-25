package com.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

	
	 Page<Attendee> findByNameContainingIgnoreCase(String name, Pageable pageable);

	    Page<Attendee> findByEmailContainingIgnoreCase(String email, Pageable pageable);

	    Page<Attendee> findByEventId(Long eventId, Pageable pageable);
}