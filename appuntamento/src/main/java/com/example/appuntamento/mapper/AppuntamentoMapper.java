package com.example.appuntamento.mapper;

import com.example.appuntamento.dto.request.AppuntamentoRequest;
import com.example.appuntamento.dto.response.AppuntamentoResponse;
import com.example.appuntamento.model.Appuntamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppuntamentoMapper {

    Appuntamento mapRequestToEntity(AppuntamentoRequest appuntamentoRequest);

    AppuntamentoResponse mapEntityToResponse(Appuntamento appuntamento);
}
