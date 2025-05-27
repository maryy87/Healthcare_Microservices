package com.example.mapper;


import com.example.dto.request.AppuntamentoRequest;
import com.example.dto.response.AppuntamentoResponse;
import com.example.model.Appuntamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppuntamentoMapper {

    Appuntamento mapRequestToEntity(AppuntamentoRequest appuntamentoRequest);

    AppuntamentoResponse mapEntityToResponse(Appuntamento appuntamento);
}
