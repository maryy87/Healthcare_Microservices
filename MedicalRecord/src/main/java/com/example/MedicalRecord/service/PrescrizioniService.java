package com.example.MedicalRecord.service;

import com.example.MedicalRecord.dto.request.PrescrizioniRequest;
import com.example.MedicalRecord.dto.response.PrescrizioniResponse;
import com.example.MedicalRecord.mapper.PrescrizioniMapper;
import com.example.MedicalRecord.model.Prescrizioni;
import com.example.MedicalRecord.repository.PrescrizioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class PrescrizioniService {


    @Autowired
    private PrescrizioniMapper prescrizioniMapper;

    @Autowired
    private PrescrizioniRepository prescrizioniRepository;

    public PrescrizioniResponse postSavePrescrizioni(PrescrizioniRequest prescrizioniRequest) {
        log.info("entrato dentro il metodo crea prescrizioni dentro PrescrizioniService");
        Prescrizioni entity = prescrizioniMapper.mapRequestPrescrizioniToEntity(prescrizioniRequest);
        entity.setDataCreaPrescrizione(LocalDate.now());
        Prescrizioni prescrizioni = prescrizioniRepository.save(entity);
        log.info("ha ritornato prescrizioni response");
        return prescrizioniMapper.mapEntityPrescrizioniToResponse(prescrizioni);

    }

    public PrescrizioniResponse updatePrescrizioni(PrescrizioniRequest prescrizioniRequest, String idPrescrizioni) {
        log.info("entrato nel metodo updatePrescrizioni dentro PrescrizioniService");
        Optional<Prescrizioni> byId = prescrizioniRepository.findById(idPrescrizioni);
        if (byId.isPresent()) {
            Prescrizioni prescrizioni = prescrizioniMapper.mapRequestPrescrizioniToEntity(prescrizioniRequest);
            prescrizioni.setIdPrescrizione(idPrescrizioni);
            prescrizioni.setDataCreaPrescrizione(byId.get().getDataCreaPrescrizione());
            Prescrizioni prescrizioni1 = prescrizioniRepository.save(prescrizioni);
            return prescrizioniMapper.mapEntityPrescrizioniToResponse(prescrizioni1);
        }
        return null;
    }

    public Boolean deletePrescrizioni(String idPrescrizioni) {
        if (prescrizioniRepository.findById(idPrescrizioni).isPresent()) {
            prescrizioniRepository.deleteById(idPrescrizioni);
            if (prescrizioniRepository.findById(idPrescrizioni).isPresent()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public PrescrizioniResponse getPrescrizioni(String idPrescrizioni) {
        Optional<Prescrizioni> optionalPrescrizioni = prescrizioniRepository.findById(idPrescrizioni);
        if (optionalPrescrizioni.isPresent()) {
            return prescrizioniMapper.mapEntityPrescrizioniToResponse(optionalPrescrizioni.get());
        }
        return null;

    }
}
