package com.example.Doctor.service;

import com.example.Doctor.Mapper.DoctorMapper;
import com.example.Doctor.dto.request.DoctorRequest;
import com.example.Doctor.dto.request.FiltroDoctorRequest;
import com.example.Doctor.dto.response.DoctorResponse;
import com.example.Doctor.model.Doctor;
import com.example.Doctor.repository.CustomDoctorRepository;
import com.example.Doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {


    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private CustomDoctorRepository customDoctorRepository;


    public List<DoctorResponse> ricercaDoctor(FiltroDoctorRequest filtroDoctorRequest) {
        if (filtroDoctorRequest != null) {
            List<Doctor> doctorFiltro = customDoctorRepository.findDoctorFiltro(filtroDoctorRequest);
            List<DoctorResponse> patientResponses = doctorMapper.mapEntityDoctorListToResponse(doctorFiltro);
            return patientResponses;
        }
        return null;
    }

    public DoctorResponse saveDoctor(DoctorRequest doctorRequest){
        Doctor doctor=doctorRepository.save(doctorMapper.mapDoctorrequestToEntity(doctorRequest));
        return doctorMapper.mapEntityDoctorToResponse(doctor);
    }

    public DoctorResponse updateDoctor(DoctorRequest doctorRequest, Long idDoctor){
        if (doctorRepository.findById(idDoctor).isPresent()) {
            Doctor doctor = doctorMapper.mapDoctorrequestToEntity(doctorRequest);
            doctor.setIdDoctor(idDoctor);
            Doctor doctor1 = doctorRepository.save(doctor);
            return doctorMapper.mapEntityDoctorToResponse(doctor1);
        }
        return null;
    }


    public Boolean deleteDoctor(Long idDoctor) {
        if (doctorRepository.findById(idDoctor).isPresent()) {
            doctorRepository.deleteById(idDoctor);
            if (doctorRepository.findById(idDoctor).isPresent()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public DoctorResponse getDoctor(Long idDoctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(idDoctor);
        if (optionalDoctor.isPresent()) {
            return doctorMapper.mapEntityDoctorToResponse(optionalDoctor.get());
        }
        return null;

    }

}
