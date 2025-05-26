package com.example.Doctor.Mapper;

import com.example.Doctor.dto.request.DoctorRequest;
import com.example.Doctor.dto.response.DoctorResponse;
import com.example.Doctor.model.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorResponse mapEntityDoctorToResponse(Doctor doctor);

    List<DoctorResponse> mapEntityDoctorListToResponse(List<Doctor> doctorList);

    Doctor mapDoctorrequestToEntity(DoctorRequest doctorRequest);
}
