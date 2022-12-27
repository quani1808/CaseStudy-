package com.order.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.order.orderservice.model.Address;

public interface AddressRepository extends MongoRepository<Address, Integer> {
	//public List<Address> findByDoctor(int id);
	public Address findByFullName(String FullName);

}
