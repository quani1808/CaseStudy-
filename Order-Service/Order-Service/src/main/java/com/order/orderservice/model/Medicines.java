package com.order.orderservice.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicines {
	
	@Id
	private int drugsId;
    private String drugsName;
    private double drugsCost;
    private int drugsQty;
    private LocalDate dateOfExpiration;

}
