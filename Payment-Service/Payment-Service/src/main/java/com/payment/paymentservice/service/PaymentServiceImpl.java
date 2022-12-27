package com.payment.paymentservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.paymentservice.repositories.StatementRepository;
import com.payment.paymentservice.model.Payment;
import com.payment.paymentservice.model.Statement;
import com.payment.paymentservice.model.TransactionType;
import com.payment.paymentservice.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	StatementRepository statementRepository;
	
	@Override
	public List<Payment> getPayment() {
		return paymentRepository.findAll();
	}
	
	@Override
	public Payment addPaymentForProfile(int profileId) {
		Payment newPayment = new Payment(profileId,0,new ArrayList<String>());
		return paymentRepository.save(newPayment);
	}
	
	@Override
	public void addMoney(int profileId, double amount) {
		// update balance
		Payment payment = paymentRepository.findByProfileId(profileId);
		
		double totalBal = amount + payment.getCurrentBalance();
		payment.setCurrentBalance(totalBal);	
		paymentRepository.save(payment);
		// generate statement
		Statement stmt = new Statement(profileId,TransactionType.CREDIT, amount, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		statementRepository.save(stmt);
	}
	
	@Override
	public void doTransaction(int profileId , double amount) {
		// update balance
		Payment payment = paymentRepository.findByProfileId(profileId);
		double totalBal =  payment.getCurrentBalance() -amount;
		payment.setCurrentBalance(totalBal);		
		paymentRepository.save(payment);
		// generate statement
		Statement stmt = new Statement(profileId,TransactionType.DEBIT, amount, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		statementRepository.save(stmt);
	}
	
	@Override
	public Payment getPaymentById(int profileId) {
		return paymentRepository.findByProfileId(profileId);
	}
	
	@Override
	public List<Statement> getStatementById(int statementId) {
		return statementRepository.findAll();
	}

	@Override
	public String deletePaymentById(int paymentId){
		Payment payment = paymentRepository.findByProfileId(paymentId);
		payment.setCurrentBalance(0);
		paymentRepository.save(payment);
		paymentRepository.delete(payment);
		return "Payment deleted!";
	}
	@Override
	public List<Statement> getAllStatements() {
		return statementRepository.findAll();
	}

}
