package com.order.orderservice.exception;

public class OrderNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    String message;
    public OrderNotFoundException(String str) {
        message = str;
    }
    
    public  OrderNotFoundException() {
        
    }



   public String toString() {
        return ("OrderNotFound WITH THE GIVEN ID"+message);
    }

}
