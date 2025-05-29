package com.example.MedicalRecord.controller;

import com.example.MedicalRecord.dto.request.PrescrizioniRequest;
import com.example.MedicalRecord.dto.response.PrescrizioniResponse;
import com.example.MedicalRecord.service.PrescrizioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/prescrizioni")
public class PrescrizioniController {

    @Autowired
    private PrescrizioniService prescrizioniService;

    @PostMapping("/postSavePrescrizioni")
    public ResponseEntity postSavePrescrizioni(@RequestBody PrescrizioniRequest prescrizioniRequest) {
        PrescrizioniResponse prescrizioniResponse = prescrizioniService.postSavePrescrizioni(prescrizioniRequest);
        if (prescrizioniResponse != null) {
            return ResponseEntity.ok(prescrizioniResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/updatePrescrizioni/{idPrescrizioni}")
    public ResponseEntity updatePrescrizioni(@RequestBody PrescrizioniRequest prescrizioniRequest, @PathVariable String idPrescrizioni) {
        PrescrizioniResponse prescrizioniResponse = prescrizioniService.updatePrescrizioni(prescrizioniRequest,idPrescrizioni);
        if (prescrizioniResponse != null) {
            return ResponseEntity.ok(prescrizioniResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/deleteMedicalRecord/{idPrescrizioni}")
    public ResponseEntity deletePrescrizioni(@PathVariable  String idPrescrizioni) {
        Boolean b = prescrizioniService.deletePrescrizioni(idPrescrizioni);
        if (b) {

            return ResponseEntity.ok("prescrizione cancellata!");
        }
        return ResponseEntity.ofNullable("la prescrizione non esiste!");
    }

    @GetMapping("/getPrescrizioni/{idPrescrizioni}")
    public ResponseEntity getPrescrizioni(@PathVariable String idPrescrizioni) {
        PrescrizioniResponse prescrizioniResponse = prescrizioniService.getPrescrizioni(idPrescrizioni);
        if (prescrizioniResponse != null) {
            return ResponseEntity.ok(prescrizioniResponse);
        }
        return ResponseEntity.notFound().build();
    }

}
