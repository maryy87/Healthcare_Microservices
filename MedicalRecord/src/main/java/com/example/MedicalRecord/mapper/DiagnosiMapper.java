package com.example.MedicalRecord.mapper;

import com.example.MedicalRecord.dto.request.DiagnosiRequest;
import com.example.MedicalRecord.dto.response.DiagnosiResponse;
import com.example.MedicalRecord.model.Diagnosi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiagnosiMapper {

    Diagnosi mapRequestDiagnosToEntity(DiagnosiRequest diagnosiRequest);

    DiagnosiResponse mapEntityDiagnosToResponse(Diagnosi diagnosi);
}
