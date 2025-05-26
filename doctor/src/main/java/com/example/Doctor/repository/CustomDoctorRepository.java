package com.example.Doctor.repository;

import com.example.Doctor.dto.request.FiltroDoctorRequest;
import com.example.Doctor.model.Doctor;

import java.util.List;

public interface CustomDoctorRepository {

    List<Doctor> findDoctorFiltro(FiltroDoctorRequest filtroDoctorRequest);

}
