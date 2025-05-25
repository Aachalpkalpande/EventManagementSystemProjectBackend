package com.exceptiondemo;

public class AttendeeNotFoundException extends RuntimeException{

	public AttendeeNotFoundException(String message) {
        super(message);
    }
}