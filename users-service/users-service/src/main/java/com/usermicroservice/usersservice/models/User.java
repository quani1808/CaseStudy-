package com.usermicroservice.usersservice.models;
  
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Document(collection="user")
public class User
{
    private int id;
    private String  fullName;
    private String gender;
    private String emailId;
    private Long mobileNumber;
    private String role;
    private String password;
    private Address address;
    
	public User(int id, String fullName, String gender, String emailId, Long mobileNumber, String role, String password,
			Address address) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.gender = gender;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.role = role;
		this.password = password;
		this.address = address;
	}
    
    
    
}