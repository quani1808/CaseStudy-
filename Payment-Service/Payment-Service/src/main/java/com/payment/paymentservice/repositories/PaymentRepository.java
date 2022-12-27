package com.payment.paymentservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.payment.paymentservice.model.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment , Integer> {
	public Payment findByProfileId(int profileId);

}
