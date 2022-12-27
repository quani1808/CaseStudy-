package com.Drug.Drugs.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Drug.Drugs.exception.CustomException;
import com.Drug.Drugs.models.Drugs;
import com.Drug.Drugs.repository.DrugsRepository;

@Service
public class DrugsService {

   @Autowired
    private DrugsRepository drugsRepository;

   @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
   
   private static final Logger LOGGER = LoggerFactory.getLogger(DrugsService.class);

   public Drugs saveDrugsDetails(Drugs drugs, MultipartFile[] file) throws CustomException{

            LOGGER.info("Sucessfully Registered new Drug");
        drugs.setDrugsId(Integer.valueOf(sequenceGeneratorService.generateSequence(drugs.SEQUENCE_NAME)));
        Drugs save = this.drugsRepository.save(drugs);  
        return save;
    }
   
    public List<Drugs> getAllDrugs() {
        return drugsRepository.findAll();
    }

    public Optional<Drugs> getDrugsByID(int drugsId) throws CustomException{
    Optional<Drugs> drugs = Optional.empty();
        try {
            if (drugsRepository.existsByDrugsId(drugsId)) {
                drugs = drugsRepository.findById(drugsId);
            } else {
                throw new CustomException("The mentioned ID is not listed");
            }
        } catch (CustomException e) {
            LOGGER.error("The mentioned ID is not listed" + e);
        }
        return drugs;
    }

    public List<Drugs> getDrugsByName(String drugsName) {
        return drugsRepository.findByDrugsName(drugsName);
    }
    

    public Drugs updateDrugsDetails(int drugsId,Drugs drugs) throws CustomException{
        Drugs    updateddrugs=drugsRepository.findById(drugsId).orElseThrow(()-> new CustomException());
       
        updateddrugs.setDrugsCost(drugs.getDrugsCost());
        updateddrugs.setDrugsId(drugs.getDrugsId());
        updateddrugs.setDrugsName(drugs.getDrugsName());
        updateddrugs.setDateOfExpiration(drugs.getDateOfExpiration());
             
        final Drugs finalUpdateddrugs= drugsRepository.save(updateddrugs);
        return finalUpdateddrugs;
       
    }

    public String deleteDrugs(int drugsId) {
        drugsRepository.deleteById(drugsId);
        return "Deleted Successfully";
    }

    /*public Drugs saveDrugsDetails(Drugs drugs) {
    	drugsRepository.save(drugs);   
        return drugs;
    }*/
    
    public Drugs addNewDrug(Drugs drug) {
        drug.setDrugsId(sequenceGeneratorService.generateSequence(Drugs.SEQUENCE_NAME));
        return drugsRepository.save(drug);
    }

}
