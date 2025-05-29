package com.example.MedicalRecord.repository;

import com.example.MedicalRecord.model.Prescrizioni;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrescrizioniRepository extends MongoRepository<Prescrizioni,String> {
}
