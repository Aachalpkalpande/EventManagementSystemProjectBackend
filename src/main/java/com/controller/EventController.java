package com.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.exceptiondemo.CategoryNotFoundException;
import com.exceptiondemo.EventNotFoundException;
import com.exceptiondemo.VenueNotFoundException;
import com.model.Category;
import com.model.Event;
import com.model.Venue;
import com.service.CategoryService;
import com.service.EventService;
import com.service.VenueService;

@RestController
@RequestMapping("events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

	
	
	 @Autowired
	    private EventService eventService;
	 
	  @Autowired
	    private VenueService venueService;

	    @Autowired
	    private CategoryService categoryService;

	    //Get all events
	 @GetMapping("/getAll")
	    public ResponseEntity<List<Event>> getAllEvents() {
	        try {
	            List<Event> events = eventService.getAllEvents();
	            return ResponseEntity.ok(events);
	        } catch (Exception e) {
	            throw new RuntimeException("Internal Server Error"); // Simulate 500
	        }
	    }
	 
	// Get event by ID
		 @GetMapping("/{id}")
		    public ResponseEntity<?> getEventById(@PathVariable Long id) {
		        try {
		            Event event = eventService.getEventById(id);
		            return ResponseEntity.ok(event);
		        } catch (Exception e) {
		        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());		        }
		    }
		
		    //Create a new event
		 @PostMapping("/create")
		 public ResponseEntity<?> createEvent(
		         @RequestParam("title") String title,
		         @RequestParam("dateTime") String dateTimeStr,
		         @RequestParam("lastRegistrationDate") String lastRegiStr,
		         @RequestParam("venueId") Long venueId,
		         @RequestParam("categoryId") Long categoryId,
		         @RequestParam(value = "image", required = false) MultipartFile imageFile) {

			 
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			        LocalDateTime dateTime;
			        LocalDateTime lastRegistrationDate;

			        // Parse dateTime safely
			        try {
			            dateTime = LocalDateTime.parse(dateTimeStr, formatter);
			        } catch (DateTimeParseException e) {
			            return ResponseEntity.badRequest().body("Invalid format for 'dateTime'. Expected format: yyyy-MM-dd'T'HH:mm");
			        }

			        // Parse lastRegistrationDate safely
			        try {
			            lastRegistrationDate = LocalDateTime.parse(lastRegiStr, formatter);
			        } catch (DateTimeParseException e) {
			            return ResponseEntity.badRequest().body("Invalid format for 'lastRegistrationDate'. Expected format: yyyy-MM-dd'T'HH:mm");
			        }
			 

		     try {
		         Event event = new Event();
		         event.setTitle(title);
		         event.setDateTime(dateTime);
		         event.setLastRegistrationDate(lastRegistrationDate);

		         
		         Venue venue = new Venue();
		         venue.setId(venueId);
		         event.setVenue(venue);

		         Category category = new Category();
		         category.setId(categoryId);
		         event.setCategory(category);

		         if (imageFile != null && !imageFile.isEmpty()) {
		             event.setImageData(imageFile.getBytes());
		             System.out.println("Image size: " + imageFile.getSize());
		         } else {
		             System.out.println("No image file received");
		         }

		         Event created = eventService.createEvent(event);
		         return ResponseEntity.status(HttpStatus.CREATED)
		                 .header("info", "Event created successfully")
		                 .body(created);

		     } catch (VenueNotFoundException e ) {
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		        }catch (CategoryNotFoundException e ) {
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		        } catch (Exception e) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		        }
			 
		 }

		 @PutMapping("/update/{id}")
		 public ResponseEntity<?> updateEvent(@PathVariable Long id,  
		          @RequestParam("title") String title,
		          @RequestParam("dateTime") String dateTimeStr,
		          @RequestParam("lastRegistrationDate") String lastRegStr,
		          @RequestParam("venueId") Long venueId,
		          @RequestParam("categoryId") Long categoryId,
		          @RequestParam(value = "image", required = false) MultipartFile imageFile) {
		     try {
		         // Fetch the existing event by ID
		         Event existingEvent = eventService.getEventById(id); // Directly get the event (no need for Optional)

		         
		      
		         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		         LocalDateTime dateTime;
		         LocalDateTime lastRegistrationDate;

		         // Parse dateTime
		         try {
		             dateTime = LocalDateTime.parse(dateTimeStr, formatter);
		         } catch (DateTimeParseException e) {
		             return ResponseEntity.badRequest().body("Invalid format for 'dateTime'. Expected format: yyyy-MM-dd'T'HH:mm");
		         }

		         // Parse lastRegistrationDate
		         try {
		             lastRegistrationDate = LocalDateTime.parse(lastRegStr, formatter);
		         } catch (DateTimeParseException e) {
		             return ResponseEntity.badRequest().body("Invalid format for 'lastRegistrationDate'. Expected format: yyyy-MM-dd'T'HH:mm");
		         } 
		         // Update fields
		         existingEvent.setTitle(title);
		         existingEvent.setDateTime(dateTime);
		         existingEvent.setLastRegistrationDate(lastRegistrationDate);

		         // Update venue and category
		         Venue venue = new Venue();
		         venue.setId(venueId);
		         existingEvent.setVenue(venue);

		         Category category = new Category();
		         category.setId(categoryId);
		         existingEvent.setCategory(category);

		         // Handle image update if a new image is provided
		         if (imageFile != null && !imageFile.isEmpty()) {
		             existingEvent.setImageData(imageFile.getBytes());
		             System.out.println("Updated Image size: " + imageFile.getSize());
		         }

		         // Save the updated event
		         Event updatedEvent = eventService.updateEvent(id, existingEvent);

		         // Return the updated event as a response
		         return ResponseEntity.ok(updatedEvent);

		     } catch (VenueNotFoundException | CategoryNotFoundException e) {
		         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		     } catch (EventNotFoundException e) {
		         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
		     } catch (Exception e) {
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
		     }
		 }


		    // Delete event by ID
		    @DeleteMapping("/delete/{id}")
		    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
		        try {
		            eventService.deleteEvent(id);
		            return ResponseEntity.ok("Event deleted successfully.");
		        } catch (EventNotFoundException e) {
			         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
			     } 
		    } 

		    
		    // Search events by title with pagination
		    @GetMapping("/search/title")
		    public ResponseEntity<Page<Event>> searchEventsByTitle(@RequestParam("keyword") String keyword,
		                                                            @RequestParam("page") int page,
		                                                            @RequestParam("size") int size) {
		        Pageable pageable = PageRequest.of(page, size);
		        Page<Event> eventPage = eventService.searchEventsByTitle(keyword, pageable);
		        return ResponseEntity.ok(eventPage);
		    }

		    // Filter events by category with pagination
		    @GetMapping("/filter/category")
		    public ResponseEntity<Page<Event>> filterEventsByCategory(@RequestParam("categoryId") Long categoryId,
		                                                               @RequestParam("page") int page,
		                                                               @RequestParam("size") int size) {
		        Pageable pageable = PageRequest.of(page, size);
		        Page<Event> eventPage = eventService.filterByCategoryId(categoryId, pageable);
		        return ResponseEntity.ok(eventPage);
		    }

		    // Filter events by venue with pagination
		    @GetMapping("/filter/venue")
		    public ResponseEntity<Page<Event>> filterEventsByVenue(@RequestParam("venueId") Long venueId,
		                                                            @RequestParam("page") int page,
		                                                            @RequestParam("size") int size) {
		        Pageable pageable = PageRequest.of(page, size);
		        Page<Event> eventPage = eventService.filterByVenueId(venueId, pageable);
		        return ResponseEntity.ok(eventPage);
		    }
}