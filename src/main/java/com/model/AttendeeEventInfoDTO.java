package com.model;
public class AttendeeEventInfoDTO {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private Long eventId;
	
	private String eventTitle;
	
	private Object eventDate;
	
	public AttendeeEventInfoDTO(Long id, String name, String email, Long eventId, String eventTitle, Object object) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventDate = object;
    }

	public AttendeeEventInfoDTO(Long id2, String name2, String email2, Long id3, String title,
			String formattedEventDate, Object imageData, String formattedRegDate) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public Object getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
}