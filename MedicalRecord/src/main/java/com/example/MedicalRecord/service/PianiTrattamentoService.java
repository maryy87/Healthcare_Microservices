package com.example.MedicalRecord.service;

import com.example.MedicalRecord.dto.request.MedicalRecordRequest;
import com.example.MedicalRecord.dto.request.PianiTrattamentoRequest;
import com.example.MedicalRecord.dto.response.MedicalRecordResponse;
import com.example.MedicalRecord.dto.response.PianiTrattamentoResponse;
import com.example.MedicalRecord.mapper.MedicalRecordMapper;
import com.example.MedicalRecord.mapper.PianiTrattamentoMapper;
import com.example.MedicalRecord.model.MedicalRecord;
import com.example.MedicalRecord.model.PianiTrattamento;
import com.example.MedicalRecord.repository.MedicalRecordRepository;
import com.example.MedicalRecord.repository.PianiTrattamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class PianiTrattamentoService {


    @Autowired
    private PianiTrattamentoMapper pianiTrattamentoMapper;

    @Autowired
    private PianiTrattamentoRepository pianiTrattamentoRepository;

    public PianiTrattamentoResponse postSavePianiTrattamento(PianiTrattamentoRequest pianiTrattamentoRequest) {
        log.info("entrato dentro il metodo crea PianiTrattamento dentro PianiTrattamentoService");
        PianiTrattamento entity = pianiTrattamentoMapper.mapRequestPianiTrattamentoToEntity(pianiTrattamentoRequest);
        PianiTrattamento pianiTrattamento = pianiTrattamentoRepository.save(entity);
        log.info("ha ritornato PianiTrattamento response");
        return pianiTrattamentoMapper.mapEntityPianiTrattamentoToResponse(pianiTrattamento);

    }

    public PianiTrattamentoResponse updatePianiTrattamento(PianiTrattamentoRequest pianiTrattamentoRequest, String idPianiTrattamento) {
        log.info("entrato nel metodo updatePianiTrattamento dentro PianiTrattamentoService");
        Optional<PianiTrattamento> byId = pianiTrattamentoRepository.findById(idPianiTrattamento);
        if (byId.isPresent()) {
            PianiTrattamento pianiTrattamento = pianiTrattamentoMapper.mapRequestPianiTrattamentoToEntity(pianiTrattamentoRequest);
            pianiTrattamento.setIdPianiTrattamento(idPianiTrattamento);
            PianiTrattamento pianiTrattamento1 = pianiTrattamentoRepository.save(pianiTrattamento);
            return pianiTrattamentoMapper.mapEntityPianiTrattamentoToResponse(pianiTrattamento1);
        }
        return null;
    }

    public Boolean deletePianiTrattamento(String idPianiTrattamento) {
        if (pianiTrattamentoRepository.findById(idPianiTrattamento).isPresent()) {
            pianiTrattamentoRepository.deleteById(idPianiTrattamento);
            if (pianiTrattamentoRepository.findById(idPianiTrattamento).isPresent()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public PianiTrattamentoResponse getPianiTrattamento(String idPianiTrattamento) {
        Optional<PianiTrattamento> optionalPianiTrattamento = pianiTrattamentoRepository.findById(idPianiTrattamento);
        if (optionalPianiTrattamento.isPresent()) {
            return pianiTrattamentoMapper.mapEntityPianiTrattamentoToResponse(optionalPianiTrattamento.get());
        }
        return null;

    }
}
