package com.example.Patient.service;

import com.example.Patient.dto.request.FiltroPatientRequest;
import com.example.Patient.dto.request.PatientRequest;
import com.example.Patient.dto.response.PatientResponse;
import com.example.Patient.mapper.PatientMapper;
import com.example.Patient.model.Patient;
import com.example.Patient.repository.CustomPatientRepository;
import com.example.Patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientMapper patientMapper;
    @Autowired
    PatientRepository patientRepository;

    @Autowired
   private  CustomPatientRepository customPatientRepository;


    public List<PatientResponse> ricercaPatient(FiltroPatientRequest filtroPatientRequest) {
        if (filtroPatientRequest != null) {
            List<Patient> patientFiltro = customPatientRepository.findPatientFiltro(filtroPatientRequest);
            List<PatientResponse> patientResponses = patientMapper.mapPatientListToResponse(patientFiltro);
            return patientResponses;
        }
        return null;
    }

    public PatientResponse savePatient(PatientRequest patientRequest) {

        Patient patient = patientRepository.save(patientMapper.mapPatientRequestToEntity(patientRequest));
        return patientMapper.mapPatientEntityToResponse(patient);
    }

    public PatientResponse updatePatient(PatientRequest patientRequest, Long idPatient) {

        if (patientRepository.findById(idPatient).isPresent()) {
            Patient patient = patientMapper.mapPatientRequestToEntity(patientRequest);
            patient.setIdPatient(idPatient);
            Patient patient1 = patientRepository.save(patient);
            return patientMapper.mapPatientEntityToResponse(patient1);
        }
        return null;
    }

    public Boolean deletePatient(Long idPatient) {
        if (patientRepository.findById(idPatient).isPresent()) {
            patientRepository.deleteById(idPatient);
            if (patientRepository.findById(idPatient).isPresent()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public PatientResponse getPatient(Long idPatient) {
        Optional<Patient> optionalPatient = patientRepository.findById(idPatient);
        if (optionalPatient.isPresent()) {
            return patientMapper.mapPatientEntityToResponse(optionalPatient.get());
        }
        return null;

    }
}
