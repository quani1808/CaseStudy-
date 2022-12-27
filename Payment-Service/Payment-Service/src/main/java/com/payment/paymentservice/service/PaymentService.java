package com.payment.paymentservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payment.paymentservice.model.Payment;
import com.payment.paymentservice.model.Statement;

@Service
public interface PaymentService {
	
	public List<Payment> getPayment();
	
	public Payment addPaymentForProfile(int profileId);
	public void addMoney(int profileId, double amount);
	
	public void doTransaction(int profileId, double amount);	
	public Payment getPaymentById(int profileId);
	public List<Statement> getStatementById(int statementId);
	public List<Statement> getAllStatements();
	public String deletePaymentById(int paymentid);

}
