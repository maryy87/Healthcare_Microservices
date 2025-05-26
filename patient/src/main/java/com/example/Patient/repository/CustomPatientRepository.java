package com.example.Patient.repository;

import com.example.Patient.dto.request.FiltroPatientRequest;
import com.example.Patient.model.Patient;

import java.util.List;


public interface CustomPatientRepository {

   List<Patient> findPatientFiltro(FiltroPatientRequest filtroPatientRequest);
}
