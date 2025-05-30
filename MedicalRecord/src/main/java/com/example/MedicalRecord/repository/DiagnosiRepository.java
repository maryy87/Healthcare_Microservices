package com.example.MedicalRecord.repository;

import com.example.MedicalRecord.model.Diagnosi;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiagnosiRepository extends MongoRepository<Diagnosi, String> {
}
