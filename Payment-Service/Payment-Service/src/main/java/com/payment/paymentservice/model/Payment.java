package com.payment.paymentservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("payment")
public class Payment {
	
	@Id
	private int paymentId;
	private int profileId;
	private double currentBalance;
	private List<String> statements;
	
	public Payment(int profileId, double currentBalance, List<String> statements) {
		super();
		
		this.profileId = profileId;
		this.currentBalance = currentBalance;
		this.statements = statements;
	}
	
	

}
