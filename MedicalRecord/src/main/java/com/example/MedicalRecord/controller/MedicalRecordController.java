package com.example.MedicalRecord.controller;

import com.example.MedicalRecord.dto.request.MedicalRecordRequest;
import com.example.MedicalRecord.dto.response.MedicalRecordResponse;
import com.example.MedicalRecord.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/medicalRecord")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping("/postSaveMedicalRecord")
    public ResponseEntity postSaveMedicalRecord(@RequestBody MedicalRecordRequest medicalRecordRequest) {
        MedicalRecordResponse medicalRecordResponse = medicalRecordService.postSaveMedicalRecord(medicalRecordRequest);
        if (medicalRecordResponse != null) {
            return ResponseEntity.ok(medicalRecordResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/updateMedicalRecord/{idMedicalRecord}")
    public ResponseEntity updateMedicalRecord(@RequestBody MedicalRecordRequest medicalRecordRequest, @PathVariable String idMedicalRecord) {
        MedicalRecordResponse medicalRecordResponse = medicalRecordService.updateMedicalRecord(medicalRecordRequest, idMedicalRecord);
        if (medicalRecordResponse != null) {
            return ResponseEntity.ok(medicalRecordResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteMedicalRecord/{idMedicalRecord}")
    public ResponseEntity deleteMedicalRecord(@PathVariable String idMedicalRecord) {
        Boolean b = medicalRecordService.deleteMedicalRecord(idMedicalRecord);
        if (b) {

            return ResponseEntity.ok("cartella medica cancellata!");
        }
        return ResponseEntity.ofNullable("la cartella medica non esiste!");
    }

    @GetMapping("/getMedicalRecord/{idMedicalRecord}")
    public ResponseEntity getMedicalRecord(@PathVariable String idMedicalRecord) {
        MedicalRecordResponse medicalRecordResponse = medicalRecordService.getMedicalRecord(idMedicalRecord);
        if (medicalRecordResponse != null) {
            return ResponseEntity.ok(medicalRecordResponse);
        }
        return ResponseEntity.notFound().build();


    }

}
