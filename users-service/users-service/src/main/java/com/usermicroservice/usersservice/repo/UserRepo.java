package com.usermicroservice.usersservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.usermicroservice.usersservice.models.User;
import com.usermicroservice.usersservice.models.User1;

@Repository
public interface UserRepo extends MongoRepository<User,Integer>
{
   public User findAllByMobileNumber(Long mobileNumber);
   public User findByFullName(String fullName);
   
}
