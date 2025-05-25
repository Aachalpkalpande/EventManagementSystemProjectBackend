package com.exceptiondemo;

public class CategoryNotFoundException extends RuntimeException{
	
	public  CategoryNotFoundException(String message) 
	{
		super(message);
	}
}