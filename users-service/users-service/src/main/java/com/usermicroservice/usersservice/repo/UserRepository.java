package com.usermicroservice.usersservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.usermicroservice.usersservice.models.User1;
@Repository 
public interface UserRepository extends MongoRepository<User1,String>{
	
	public User1 findByUsername(String username);

}
