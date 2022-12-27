package com.Drug.Drugs.controller;

import java.util.List;
import java.util.Optional;



import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.Drug.Drugs.exception.CustomException;
import com.Drug.Drugs.models.Drugs;
//import com.Drug.Drugs.models.Inventory;
import com.Drug.Drugs.service.DrugsService;



@RestController
@RequestMapping("/drugs")
public class DrugsController {
    
    @Autowired
    private DrugsService service;    //Service

     @PostMapping("/add")
     public ResponseEntity<Drugs> addDrug (@RequestBody Drugs drug)
     {
        Drugs addDrugs = service.addNewDrug(drug);
        return new ResponseEntity<>(addDrugs,HttpStatus.CREATED);
     }
 
     //Get all the list of drugs available
     @GetMapping("/list")
     public List<Drugs> getAllDrugs()
     {
        return service.getAllDrugs();
     }
     
     //Fetch a particular drugs using the drugs id as Path Variable
     @GetMapping("/getbyid/{drugsId}")
     public Optional<Drugs> getDrugsById(@PathVariable("drugsId") int drugsId) throws CustomException, Exception
     {
         return service.getDrugsByID(drugsId);
     }
     
    
     @GetMapping("/drugs-name/{drugsname}")
     public List<Drugs> getDrugsByName(@PathVariable("drugsname") String drugsName) throws CustomException, Exception 
     {
         return service.getDrugsByName(drugsName);
     }
     
     //For updating the drugs to its respective attribute
     @PutMapping("/update/{id}")
     public ResponseEntity<Drugs> updateDrugsDetails( @PathVariable int id,@Valid @RequestBody Drugs drugs) throws CustomException, Exception 
     {
        return ResponseEntity.ok(service.updateDrugsDetails(id,drugs));
     }
 
     //Deleting the drugs respective to the specified drugs id
     @DeleteMapping("/{id}")
      public String deleteDrugs(@PathVariable("id") int drugsId) 
      {
          return service.deleteDrugs(drugsId);    
      }

}
