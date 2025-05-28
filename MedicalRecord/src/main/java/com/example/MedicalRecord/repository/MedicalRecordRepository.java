package com.example.MedicalRecord.repository;

import com.example.MedicalRecord.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord,String> {
}
