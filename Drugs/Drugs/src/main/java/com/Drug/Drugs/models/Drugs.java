package com.Drug.Drugs.models;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Document(collection="drugs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drugs {
	
	@Transient
    public static final String SEQUENCE_NAME = "drugs_sequence";
    
    @Id
    private int drugsId;
    private String drugsName;
    private double drugsCost;
    private int drugsQty;
    private LocalDate dateOfExpiration;
}
