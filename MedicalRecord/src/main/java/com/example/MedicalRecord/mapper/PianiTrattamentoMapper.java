package com.example.MedicalRecord.mapper;

import com.example.MedicalRecord.dto.request.MedicalRecordRequest;
import com.example.MedicalRecord.dto.request.PianiTrattamentoRequest;
import com.example.MedicalRecord.dto.response.MedicalRecordResponse;
import com.example.MedicalRecord.dto.response.PianiTrattamentoResponse;
import com.example.MedicalRecord.model.MedicalRecord;
import com.example.MedicalRecord.model.PianiTrattamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PianiTrattamentoMapper {

    PianiTrattamento mapRequestPianiTrattamentoToEntity(PianiTrattamentoRequest pianiTrattamentoRequest);
    PianiTrattamentoResponse mapEntityPianiTrattamentoToResponse(PianiTrattamento pianiTrattamento);
}
