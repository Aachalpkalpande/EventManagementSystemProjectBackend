package com.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.*;



@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @NotNull(message = "Title can't be null")
    private String title;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    
    // Many Events can belong to one Category (ManyToOne)
    // The owning side uses @JoinColumn
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference 
    private Category category;

    // Many Events can happen at one Venue (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "venue_id")
    @JsonBackReference 
    private Venue venue;

    // One Event can have many Attendees (OneToMany)
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    @JsonManagedReference 
    private List<Attendee> attendees;

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(Long id, String title, String description, LocalDateTime dateTime, Category category, Venue venue,
			List<Attendee> attendees) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dateTime = dateTime;
		this.category = category;
		this.venue = venue;
		this.attendees = attendees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public List<Attendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}

	public void setLastRegistrationDate(LocalDateTime lastRegistrationDate) {
		this.setLastRegistrationDate(lastRegistrationDate);
	}

	public void setImageData(Object object) {
	
		
	}

	public Object getImageData() {
		
		return null;
	}

	public DateTimeFormatter getLastRegistrationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLastRegistrationDate(DateTimeFormatter lastRegistrationDate) {
		// TODO Auto-generated method stub
		
	}

	public String formattedEventDate() {
		// TODO Auto-generated method stub
		return null;
	}

}