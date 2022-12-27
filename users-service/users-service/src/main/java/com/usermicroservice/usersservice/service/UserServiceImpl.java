package com.usermicroservice.usersservice.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//import com.casestudy.exception.ProductNotFoundException;
//import com.casestudy.productservice.entity.Product;
import com.usermicroservice.usersservice.exception.UserNotFoundException;
import com.usermicroservice.usersservice.models.User;
import com.usermicroservice.usersservice.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    UserRepo userRepo;


   @Override
    public User addNewDoctorUser(User user) {
       return userRepo.save(user);
    }

   @Override
    public List<User> getAllUsers() {
       return userRepo.findAll();
    }

   @Override
    public Optional<User> getByUserId(int userId){
        return Optional.empty();
    }

   @Override
    public User updateUser (User user){
        return userRepo.save(user);
        
    }



   @Override
    public void deleteUser(int userId){
        userRepo.deleteById(userId);
   }



   @Override
    public User addNewAdminUser(User user) {
        return userRepo.save(user);
   }



   @Override
    public User addNewDelivaryUser(User user) {
        return userRepo.save(user);
   }



   @Override
    public User findByMobileNo(Long mobileNumber) {
        
        return userRepo.findAllByMobileNumber(mobileNumber);
    }



   @Override
    public User getByUserName(String fullName) {
        
        return userRepo.findByFullName(fullName);
    }



}
