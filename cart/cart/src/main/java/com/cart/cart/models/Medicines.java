package com.cart.cart.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

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
