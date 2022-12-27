package com.order.orderservice.model;

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
  private String fullName;
}
