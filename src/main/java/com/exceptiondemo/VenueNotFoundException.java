package com.exceptiondemo;

public class VenueNotFoundException extends RuntimeException{
	
	  public VenueNotFoundException(String message) 
	  {
		  super(message);
		  
	  }
}