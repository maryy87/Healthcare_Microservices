package com.example.MedicalRecord.service;

import com.example.MedicalRecord.dto.request.DiagnosiRequest;
import com.example.MedicalRecord.dto.request.RisultatiEsameLaboratorioRequest;
import com.example.MedicalRecord.dto.response.DiagnosiResponse;
import com.example.MedicalRecord.dto.response.RisultatiEsameLaboratorioResponse;
import com.example.MedicalRecord.mapper.RisultatiEsameLaboratorioMapper;
import com.example.MedicalRecord.model.Diagnosi;
import com.example.MedicalRecord.model.RisultatiEsameLaboratorio;
import com.example.MedicalRecord.repository.RisultatiEsameLaboratorioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RisultatiEsameLaboratorioService {


    @Autowired
    private RisultatiEsameLaboratorioMapper risultatiEsameLaboratorioMapper;

    @Autowired
    private RisultatiEsameLaboratorioRepository risultatiEsameLaboratorioRepository;

    public RisultatiEsameLaboratorioResponse postSaveRisultatiEsameLaboratorio (RisultatiEsameLaboratorioRequest risultatiEsameLaboratorioRequest) {
        log.info("entrato dentro il metodo crea RisultatiEsameLaboratorio dentro RisultatiEsameLaboratorioService");
        RisultatiEsameLaboratorio entity = risultatiEsameLaboratorioMapper.mapRequestRisultatiEsameLaboratorioToEntity(risultatiEsameLaboratorioRequest);
        RisultatiEsameLaboratorio risultatiEsameLaboratorio = risultatiEsameLaboratorioRepository.save(entity);
        log.info("ha ritornato RisultatiEsameLaboratorio response");
        return risultatiEsameLaboratorioMapper.mapEntityRisultatiEsameLaboratorioToResponse(risultatiEsameLaboratorio);

    }

    public RisultatiEsameLaboratorioResponse updateRisultatiEsameLaboratorio(RisultatiEsameLaboratorioRequest risultatiEsameLaboratorioRequest, String idRisultatiEsameLaboratorio) {
        log.info("entrato nel metodo updateRisultatiEsameLaboratorio dentro RisultatiEsameLaboratorioService");
        Optional<RisultatiEsameLaboratorio> byId = risultatiEsameLaboratorioRepository.findById(idRisultatiEsameLaboratorio);
        if (byId.isPresent()) {
            RisultatiEsameLaboratorio risultatiEsameLaboratorio = risultatiEsameLaboratorioMapper.mapRequestRisultatiEsameLaboratorioToEntity(risultatiEsameLaboratorioRequest);
            risultatiEsameLaboratorio.setIdRisultatiEsameLaboratorio(idRisultatiEsameLaboratorio);
            RisultatiEsameLaboratorio risultatiEsameLaboratorio1 = risultatiEsameLaboratorioRepository.save(risultatiEsameLaboratorio);
            return risultatiEsameLaboratorioMapper.mapEntityRisultatiEsameLaboratorioToResponse(risultatiEsameLaboratorio1);
        }
        return null;
    }

    public Boolean deleteRisultatiEsameLaboratorio(String idRisultatiEsameLaboratorio) {
        if (risultatiEsameLaboratorioRepository.findById(idRisultatiEsameLaboratorio).isPresent()) {
            risultatiEsameLaboratorioRepository.deleteById(idRisultatiEsameLaboratorio);
            if (risultatiEsameLaboratorioRepository.findById(idRisultatiEsameLaboratorio).isPresent()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public RisultatiEsameLaboratorioResponse getRisultatiEsameLaboratorio(String idRisultatiEsameLaboratorio) {
        Optional<RisultatiEsameLaboratorio> byId = risultatiEsameLaboratorioRepository.findById(idRisultatiEsameLaboratorio);
        if (byId.isPresent()) {
            return risultatiEsameLaboratorioMapper.mapEntityRisultatiEsameLaboratorioToResponse(byId.get());
        }
        return null;

    }
}
