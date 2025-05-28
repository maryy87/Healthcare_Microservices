package com.example.MedicalRecord.mapper;

import com.example.MedicalRecord.dto.request.MedicalRecordRequest;
import com.example.MedicalRecord.dto.response.MedicalRecordResponse;
import com.example.MedicalRecord.model.MedicalRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    MedicalRecord mapRequestMedicalRecordToEntity(MedicalRecordRequest medicalRecordRequest);
    MedicalRecordResponse mapEntityMedicalRecordToResponse(MedicalRecord medicalRecord);
}
