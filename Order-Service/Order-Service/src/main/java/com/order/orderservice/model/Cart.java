package com.order.orderservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="cart")
public class Cart {
    
	 @Id
	    private int cartId;
	    private List<Medicines> medicines;
	    private double totalPrice;
    
  
}
