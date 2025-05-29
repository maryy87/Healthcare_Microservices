package com.example.MedicalRecord.mapper;

import com.example.MedicalRecord.dto.request.MedicalRecordRequest;
import com.example.MedicalRecord.dto.request.PrescrizioniRequest;
import com.example.MedicalRecord.dto.response.MedicalRecordResponse;
import com.example.MedicalRecord.dto.response.PrescrizioniResponse;
import com.example.MedicalRecord.model.MedicalRecord;
import com.example.MedicalRecord.model.Prescrizioni;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrescrizioniMapper {

    Prescrizioni mapRequestPrescrizioniToEntity(PrescrizioniRequest prescrizioniRequest);
    PrescrizioniResponse mapEntityPrescrizioniToResponse(Prescrizioni prescrizioni);
}
