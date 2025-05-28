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
import java.util.Optional;

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

    public AppuntamentoResponse updateAppuntamento(AppuntamentoRequest appuntamentoRequest, String idAppuntamento) {
        log.info("entrato nel metodo updateAppuntamento dentro appuntamento");
        Optional<Appuntamento> byId = appuntamentoRepository.findById(idAppuntamento);
        if (byId.isPresent()) {
            Appuntamento appuntamento = appuntamentoMapper.mapRequestToEntity(appuntamentoRequest);
            appuntamento.setIdAppuntamento(idAppuntamento);
            appuntamento.setDataCreazioneAppuntamento(byId.get().getDataCreazioneAppuntamento());
            Appuntamento appuntamento1 = appuntamentoRepository.save(appuntamento);
            return appuntamentoMapper.mapEntityToResponse(appuntamento1);
        }
        return null;
    }

    public Boolean deleteAppuntamento(String idAppuntamento) {
        if (appuntamentoRepository.findById(idAppuntamento).isPresent()) {
            appuntamentoRepository.deleteById(idAppuntamento);
            if (appuntamentoRepository.findById(idAppuntamento).isPresent()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public AppuntamentoResponse getAppuntamento(String idAppuntamento) {
        Optional<Appuntamento> optionalAppuntamento = appuntamentoRepository.findById(idAppuntamento);
        if (optionalAppuntamento.isPresent()) {
            return appuntamentoMapper.mapEntityToResponse(optionalAppuntamento.get());
        }
        return null;

    }


}
