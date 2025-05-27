package com.example.service;


import com.example.dto.request.AppuntamentoRequest;
import com.example.dto.response.AppuntamentoResponse;
import com.example.mapper.AppuntamentoMapper;
import com.example.model.Appuntamento;
import com.example.repository.AppuntamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AppuntamentoService {

    @Autowired
    private AppuntamentoRepository appuntamentoRepository;
    @Autowired
    private AppuntamentoMapper appuntamentoMapper;

    public AppuntamentoResponse postSaveAppuntamento(AppuntamentoRequest appuntamentoRequest) {
        log.info("entrato dentro il metodo crea appuntamento dentro appuntamentoService");
        Appuntamento entity = appuntamentoMapper.mapRequestToEntity(appuntamentoRequest);
        entity.setDataCreazioneAppuntamento(LocalDateTime.now());
        Appuntamento appuntamento = appuntamentoRepository.save(entity);
        log.info("ha ritornato apuntamento response");
        return appuntamentoMapper.mapEntityToResponse(appuntamento);


    }



}
