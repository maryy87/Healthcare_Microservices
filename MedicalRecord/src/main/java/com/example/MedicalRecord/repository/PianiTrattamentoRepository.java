package com.example.MedicalRecord.repository;

import com.example.MedicalRecord.model.PianiTrattamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PianiTrattamentoRepository extends MongoRepository<PianiTrattamento,String> {
}
