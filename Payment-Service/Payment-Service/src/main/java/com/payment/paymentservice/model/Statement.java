package com.payment.paymentservice.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Document("Statement")
public class Statement {
	
	
	private int statementId;
	private TransactionType transactionType;
	private double amount;
	
	private LocalDateTime localDateTime;
	private int orderId;
	//private Payment payment;
	
	public Statement() {
		super();
	}

	public Statement(int statementId, TransactionType transactionType, double amount, LocalDateTime localDateTime) {
		super();
		this.statementId = statementId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.localDateTime = localDateTime;
		//this.payment = payment;
	} 
	
	

}
