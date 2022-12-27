package com.usermicroservice.usersservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address
{
private int houseNumber;
private String streetName;
private String colonyName;
private String city;
private String State;
private int pincode;

}
