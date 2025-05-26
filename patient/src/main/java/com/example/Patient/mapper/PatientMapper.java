package com.example.Patient.mapper;

import com.example.Patient.dto.request.PatientRequest;
import com.example.Patient.dto.response.PatientResponse;
import com.example.Patient.model.Patient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {


    Patient mapPatientRequestToEntity(PatientRequest patientRequest);


    List<Patient> mapPatientRequestListToEntity(List<PatientRequest> patientRequest);

    PatientResponse mapPatientEntityToResponse(Patient patient);

    List<PatientResponse> mapPatientListToResponse(List<Patient> patient);
}
