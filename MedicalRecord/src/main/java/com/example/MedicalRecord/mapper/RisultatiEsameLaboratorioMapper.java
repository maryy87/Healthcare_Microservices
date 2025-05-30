package com.example.MedicalRecord.mapper;

import com.example.MedicalRecord.dto.request.RisultatiEsameLaboratorioRequest;
import com.example.MedicalRecord.dto.response.RisultatiEsameLaboratorioResponse;
import com.example.MedicalRecord.model.RisultatiEsameLaboratorio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RisultatiEsameLaboratorioMapper {

    RisultatiEsameLaboratorio mapRequestRisultatiEsameLaboratorioToEntity(RisultatiEsameLaboratorioRequest risultatiEsameLaboratorioRequest);

    RisultatiEsameLaboratorioResponse mapEntityRisultatiEsameLaboratorioToResponse(RisultatiEsameLaboratorio risultatiEsameLaboratorio);
}
