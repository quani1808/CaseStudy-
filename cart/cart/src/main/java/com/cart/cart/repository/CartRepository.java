package com.cart.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cart.cart.models.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, Integer>{
	

	Cart findByCartId(int cartId);
	
}
