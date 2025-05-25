package com.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.model.Attendee;
import com.model.AttendeeEventInfoDTO;

public interface AttendeeService {

	List<Attendee> getAllAttendees();
    Attendee getAttendeeById(Long id);
    AttendeeEventInfoDTO createAttendee(Long eventId, Attendee attendee);
    Attendee updateAttendee(Long id, Attendee attendee);
    void deleteAttendee(Long id);
    
    Page<Attendee> searchByName(String name, Pageable pageable);
	Page<Attendee> searchByEmail(String email, Pageable pageable);
	Page<Attendee> filterByEventId(Long eventId, Pageable pageable);
}