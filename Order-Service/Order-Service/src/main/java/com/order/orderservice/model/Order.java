package com.order.orderservice.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Order {
	public static final String SEQUENCE_NAME = "order_sequence";
	
	@Id
	private int orderId;
	private LocalDate orderDate;
	private int userId;
	private String orderStatus;
	private List<Address> address;
	private String modeOfPayment;
	private String fullName;
	private double amountPaid;
	private double total;
	
	public static String getSequensename() {
		return SEQUENCE_NAME;
	}
}