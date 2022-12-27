package com.usermicroservice.usersservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

 

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

 

import com.usermicroservice.usersservice.exception.UserNotFoundException;
import com.usermicroservice.usersservice.models.Address;
import com.usermicroservice.usersservice.models.User;
import com.usermicroservice.usersservice.repo.UserRepo;
import com.usermicroservice.usersservice.service.UserService;

@SpringBootTest
public class UserControllerTest {

 

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepo userRepo;
    
   
    @Test
    public void getAllUserTest()
    {
        Address address=new Address(8987,"hs","has","ajh","hsud",89765);
        List<User> users=new ArrayList<>();
        users.add(new User(4,"sathya","Male","sathya@gmial.com",7364839873L,"Doctor","Sathya",address));
        
        when(userRepo.findAll()).thenReturn(users);
        assertEquals(1,userService.getAllUsers().size());

    }
    @Test
    public void addNewDoctorUserTest()
    {
        Address address=new Address(6789,"GaneshStreet","SvnColony","Guntur","AndhraPradesh",89765);
        User user=new User(7,"Astha","Female","astha@gmial.com",7364839897L,"Doctor","Astha",address);
        when(userService.addNewDoctorUser(user)).thenReturn(user);
        assertEquals(user,userRepo.save(user));

    }
    @Test
    public void addNewAdminUserTest()
    {
        Address address=new Address(7865,"MallakStreet","ParakshColony","Bellandhur","Bangalore",56789);
        User user=new User(8,"Surya","Male","surya@gmail.com",9364839897L,"Admin","Surya",address);
        when(userRepo.save(user)).thenReturn(user);
        assertEquals(user,userService.addNewAdminUser(user));
    }
    @Test
    public void addNewDeliveryAgentUserTest()
    {
        Address address=new Address(2098,"BandarStreet","MallakColony","Marthali","Bangalore",56788);
        User user=new User(9,"Sandeep","Male","sandeep@gmail.com",9634839897L,"deliveryAgent","Sandeep",address);
        when(userService.addNewDelivaryUser(user)).thenReturn(user);
        assertEquals(user,userRepo.save(user));
    }
    @Test
    public void findByMobileNumberTest()
    {
      //  Address address =new ArrayList<>();
        Address address=new Address(123,"mpp school","mpp","prakasam","ap",8756);
        User user=new User(9,"Sandeep","Male","sandeep@gmail.com",9634839897L,"doctor","sandeep@123",address);
        Long number=7655678291L;
        
        when(userRepo.findAllByMobileNumber(number))
        .thenReturn(user);
        assertEquals(user,userService.findByMobileNo(number));
    }
    
    @Test
    public void findByUserNameTest()
    {
     //   List<Address> address =new ArrayList<>();
        Address address=new Address(123,"mpp school","mpp","prakasam","ap",8756);
        User user = new User(1,"Srimanya Gangavarapu","female","srimanya@gmail.com", 7655678291L,"customer","Manya@123",address);
        String userName=(String) toString();
        userName="Srimanya Gangavarapu";

        when(userRepo.findByFullName(userName)) 
        .thenReturn(user);
        assertEquals(user,userService.getByUserName(userName));
    }
    
    @Test
    public void throwUserNotFoundByIdExceptionTest()
    {
        Address address=new Address(2098,"BandarStreet","MallakColony","Marthali","Bangalore",56788);
        User user=new User(1,"Vandeep","Male","Vandeep@gmail.com",8634839897L,"deliveryAgent","Vandeep",address);

        int id=1;
        if(userRepo.existsById(id))
        {
            return;
        }
        else
        {
           assertThat(String.format("Id bot found",id));
        }

    }
    @Test
    public void updateUserTest()
    {
        Address address=new Address(2098,"BandarStreet","MallakColony","Marthali","Bangalore",56788);
        User user=new User(9,"Sathya","Female","sathya@gmail.com",9634839897L,"deliveryAgent","Sathya",address);
        when(userRepo.save(user)).thenReturn(user);
        assertEquals(user,userService.updateUser(user));
    }
    @Test
    public void deleteByUserIdTest()
    {
        Address address=new Address(2098,"BandarStreet","MallakColony","Marthali","Bangalore",56788);
        User user=new User(9,"Ganesh","Male","ganesh@gmail.com",9634839897L,"deliveryAgent","Ganesh",address);
        int id=9;
     userRepo.deleteById(id);
        
    }
}