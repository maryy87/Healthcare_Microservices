package com.example.MedicalRecord.service;

import com.example.MedicalRecord.dto.request.MedicalRecordRequest;
import com.example.MedicalRecord.dto.response.MedicalRecordResponse;
import com.example.MedicalRecord.mapper.MedicalRecordMapper;
import com.example.MedicalRecord.model.MedicalRecord;
import com.example.MedicalRecord.repository.MedicalRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class MedicalRecordService {


    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordResponse postSaveMedicalRecord(MedicalRecordRequest medicalRecordRequest) {
        log.info("entrato dentro il metodo crea cartella medica dentro MedicalRecordService");
        MedicalRecord entity = medicalRecordMapper.mapRequestMedicalRecordToEntity(medicalRecordRequest);
        entity.setDataCreazioneCartellaMedica(LocalDateTime.now());
        MedicalRecord medicalRecord = medicalRecordRepository.save(entity);
        log.info("ha ritornato MedicalRecord response");
        return medicalRecordMapper.mapEntityMedicalRecordToResponse(medicalRecord);

    }

    public MedicalRecordResponse updateMedicalRecord(MedicalRecordRequest medicalRecordRequest, String idMedicalRecord) {
        log.info("entrato nel metodo updateMedicalRecord dentro MedicalRecordService");
        Optional<MedicalRecord> byId = medicalRecordRepository.findById(idMedicalRecord);
        if (byId.isPresent()) {
            MedicalRecord medicalRecord = medicalRecordMapper.mapRequestMedicalRecordToEntity(medicalRecordRequest);
            medicalRecord.setIdMedicalRecord(idMedicalRecord);
            medicalRecord.setDataCreazioneCartellaMedica(byId.get().getDataCreazioneCartellaMedica());
            MedicalRecord medicalRecord1 = medicalRecordRepository.save(medicalRecord);
            return medicalRecordMapper.mapEntityMedicalRecordToResponse(medicalRecord1);
        }
        return null;
    }

    public Boolean deleteMedicalRecord(String idMedicalRecord) {
        if (medicalRecordRepository.findById(idMedicalRecord).isPresent()) {
            medicalRecordRepository.deleteById(idMedicalRecord);
            if (medicalRecordRepository.findById(idMedicalRecord).isPresent()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public MedicalRecordResponse getMedicalRecord(String idMedicalRecord) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordRepository.findById(idMedicalRecord);
        if (optionalMedicalRecord.isPresent()) {
            return medicalRecordMapper.mapEntityMedicalRecordToResponse(optionalMedicalRecord.get());
        }
        return null;

    }
}
