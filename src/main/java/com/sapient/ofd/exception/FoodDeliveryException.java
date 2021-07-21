package com.sapient.ofd.exception;

public class FoodDeliveryException extends Exception{
	private static final long serialVersionUID = 1L;	
	

	public FoodDeliveryException() {
		super("Unable to receive food order");
	}

	public FoodDeliveryException(String message) {
		super(message);	
	}


	public FoodDeliveryException(String message,Throwable e) {
		super(message,e);
	}

}
