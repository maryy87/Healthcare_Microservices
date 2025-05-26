package com.example.repository;

import com.example.model.Appuntamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppuntamentoRepository extends MongoRepository<Appuntamento,Long> {
}
