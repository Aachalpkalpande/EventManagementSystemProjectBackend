package com.service;

import java.time.format.DateTimeFormatter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.AttendeeRepository;
import com.dao.EventRepository;
import com.exceptiondemo.AttendeeNotFoundException;
import com.exceptiondemo.EventNotFoundException;
import com.model.Attendee;
import com.model.AttendeeEventInfoDTO;
import com.model.Event;

@Service
public class AttendeeServiceImpl implements AttendeeService{

	@Autowired
	private AttendeeRepository attendeeRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Override
	public List<Attendee> getAllAttendees() {
		return attendeeRepo.findAll();
	}
	@Override
	public Attendee getAttendeeById(Long id) {
		return attendeeRepo.findById(id).orElseThrow(() -> new  AttendeeNotFoundException("Attendee with ID " + id + " not found"));
	}

	
    
	@Override
	public AttendeeEventInfoDTO createAttendee(Long eventId, Attendee attendee) {
		// TODO Auto-generated method stub
		
		 // Find the event by ID or throw an exception if not found
		 Event event = eventRepo.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event with ID " + eventId + " not found"));
		 
		 // Set the event inside the attendee before saving
		 attendee.setEvent(event);
		 
		 // Save the attendee to the database
		 Attendee saved = attendeeRepo.save(attendee);
	     
		 // Format the event date as a String
		    String formattedDate = event.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		    
//		    String formattedRegDate = event.getLastRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		// Return the custom DTO with combined attendee and event info
		 return new AttendeeEventInfoDTO(
	                saved.getId(),               // Attendee ID
	                saved.getName(),             // Attendee name
	                saved.getEmail(),            // Attendee email
	                event.getId(),               // Event ID
	                event.getTitle(),            // Event title
	           
	                event.getImageData()        //Event Image
	               // formattedRegDate               // Event last Regdate as string
	                );

	                
	}

	
	

	@Override
    public Attendee updateAttendee(Long id, Attendee updated) {
        Attendee attendee = attendeeRepo.findById(id)
                .orElseThrow(() -> new AttendeeNotFoundException("Attendee with ID " + id + " not found"));

        attendee.setName(updated.getName());
        attendee.setEmail(updated.getEmail());

        return attendeeRepo.save(attendee);
    }
	
	@Override
	public void deleteAttendee(Long id) {
	    Attendee attendee = attendeeRepo.findById(id)
	        .orElseThrow(() -> new AttendeeNotFoundException("Attendee with ID " + id + " not found"));

	    attendeeRepo.delete(attendee);
	}


	 @Override
	    public Page<Attendee> searchByName(String name, Pageable pageable) {
	        return attendeeRepo.findByNameContainingIgnoreCase(name, pageable);
	    }
	
	    @Override
	    public Page<Attendee> searchByEmail(String email, Pageable pageable) {
	        return attendeeRepo.findByEmailContainingIgnoreCase(email, pageable);
	    }
		
	    @Override
	    public Page<Attendee> filterByEventId(Long eventId, Pageable pageable) {
	        return attendeeRepo.findByEventId(eventId, pageable);
	    }

	
}