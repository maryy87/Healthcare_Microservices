package com.example.appuntamento.repository;


import com.example.appuntamento.model.Appuntamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppuntamentoRepository extends MongoRepository<Appuntamento,String> {
}
