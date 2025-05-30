package com.example.MedicalRecord.repository;

import com.example.MedicalRecord.model.RisultatiEsameLaboratorio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RisultatiEsameLaboratorioRepository extends MongoRepository<RisultatiEsameLaboratorio, String> {
}
