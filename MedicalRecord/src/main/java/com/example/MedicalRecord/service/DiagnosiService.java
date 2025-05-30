package com.example.MedicalRecord.service;

import com.example.MedicalRecord.dto.request.DiagnosiRequest;
import com.example.MedicalRecord.dto.response.DiagnosiResponse;
import com.example.MedicalRecord.mapper.DiagnosiMapper;
import com.example.MedicalRecord.model.Diagnosi;
import com.example.MedicalRecord.repository.DiagnosiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DiagnosiService {


    @Autowired
    private DiagnosiMapper diagnosiMapper;

    @Autowired
    private DiagnosiRepository diagnosiRepository;

    public DiagnosiResponse postSaveDiagnosi(DiagnosiRequest diagnosiRequest) {
        log.info("entrato dentro il metodo crea Diagnosi dentro DiagnosiService");
        Diagnosi entity = diagnosiMapper.mapRequestDiagnosToEntity(diagnosiRequest);
        Diagnosi diagnosi = diagnosiRepository.save(entity);
        log.info("ha ritornato Diagnosi response");
        return diagnosiMapper.mapEntityDiagnosToResponse(diagnosi);

    }

    public DiagnosiResponse updateDiagnosi(DiagnosiRequest diagnosiRequest, String idDiagnosi) {
        log.info("entrato nel metodo updateDiagnosi dentro DiagnosiService");
        Optional<Diagnosi> byId = diagnosiRepository.findById(idDiagnosi);
        if (byId.isPresent()) {
            Diagnosi diagnosi = diagnosiMapper.mapRequestDiagnosToEntity(diagnosiRequest);
            diagnosi.setIdDiagnosi(idDiagnosi);
            Diagnosi diagnosi1 = diagnosiRepository.save(diagnosi);
            return diagnosiMapper.mapEntityDiagnosToResponse(diagnosi1);
        }
        return null;
    }

    public Boolean deleteDiagnosi(String idDiagnosi) {
        if (diagnosiRepository.findById(idDiagnosi).isPresent()) {
            diagnosiRepository.deleteById(idDiagnosi);
            if (diagnosiRepository.findById(idDiagnosi).isPresent()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public DiagnosiResponse getDiagnosi(String idDiagnosi) {
        Optional<Diagnosi> optionalDiagnosi = diagnosiRepository.findById(idDiagnosi);
        if (optionalDiagnosi.isPresent()) {
            return diagnosiMapper.mapEntityDiagnosToResponse(optionalDiagnosi.get());
        }
        return null;

    }
}
