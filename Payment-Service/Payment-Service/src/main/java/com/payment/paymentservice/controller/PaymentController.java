package com.payment.paymentservice.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.paymentservice.model.Payment;
import com.payment.paymentservice.model.Statement;
import com.payment.paymentservice.repositories.StatementRepository;
import com.payment.paymentservice.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	StatementRepository statementRepository;
	
	@GetMapping("/all")
	public List<Payment> getPayments(){
		return paymentService.getPayment();
	}
	
	@PostMapping("/addForProfile/{profileId}")
	public Payment addPaymentForProfile(@PathVariable("profileId") int profileId) {
		return paymentService.addPaymentForProfile(profileId);
	}
	
	@PostMapping("/addmoney/{amount}")
	public String onlinePayment( @PathVariable int amount ) throws RazorpayException {
		
		int amt= amount;
		System.out.println(amt);
		
		 RazorpayClient client =  new RazorpayClient("rzp_test_tdoFdvQSq6X8Jz","TXFx3U61QhiBfOLZyki27h6t");
		 JSONObject options = new JSONObject();
		 options.put("amount", amt*100);
		 options.put("currency", "INR");
		 options.put("receipt", "txn_123456");
		 Order order = client.Orders.create(options);
		 System.out.println(order);
		 return order.toString();
	}
	
	@PostMapping("/addMoneyPayment/{amount}/{profileId}")
	public void  addPaymant(@PathVariable int amount,@PathVariable int profileId) {
		 paymentService.addMoney(profileId, amount);
	}
	@PostMapping("/transaction/{amount}/{profileId}")
	public void doTransaction(@PathVariable int profileId, @PathVariable("amount") double amount) {
		paymentService.doTransaction(profileId, amount);
	}
	
	@GetMapping("/getById/{profileId}")
	public Payment getPaymentById(@PathVariable("profileId") int profileId){
		return paymentService.getPaymentById(profileId);
	}
	
	@GetMapping("/statement/byid/{statementId}")
	public List<Statement> getStatementById(@PathVariable("statementId") int statementId){
		return paymentService.getStatementById(statementId);
	}
	
	@GetMapping("/statements/all")
	public List<Statement> getAllStatements(){
		return paymentService.getAllStatements();
	}
	
	@DeleteMapping("/delete/{paymentId}")
	public String deletePaymentByPaymentId(@PathVariable("paymentId") int paymentId) {
		return paymentService.deletePaymentById(paymentId);
	}
	@DeleteMapping("/deleteS/{profileId}")
	public String deletePaymentById(@PathVariable("profileId") int profileId) {
		statementRepository.deleteById(profileId);
		return "done";
	}
	

}
