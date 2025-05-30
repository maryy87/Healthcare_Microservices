package com.example.MedicalRecord.controller;

import com.example.MedicalRecord.dto.request.DiagnosiRequest;
import com.example.MedicalRecord.dto.response.DiagnosiResponse;
import com.example.MedicalRecord.service.DiagnosiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/diagnosi")
public class DiagnosiController {

    @Autowired
    private DiagnosiService diagnosiService;

    @PostMapping("/postSaveDiagnosi")
    public ResponseEntity postSaveDiagnosi(@RequestBody DiagnosiRequest diagnosiRequest) {
        DiagnosiResponse diagnosiResponse = diagnosiService.postSaveDiagnosi(diagnosiRequest);
        if (diagnosiResponse != null) {
            return ResponseEntity.ok(diagnosiResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/updateDiagnosi/{idDiagnosi}")
    public ResponseEntity updateDiagnosi(@RequestBody DiagnosiRequest diagnosiRequest, @PathVariable String idDiagnosi) {
        DiagnosiResponse diagnosiResponse = diagnosiService.updateDiagnosi(diagnosiRequest, idDiagnosi);
        if (diagnosiResponse != null) {
            return ResponseEntity.ok(diagnosiResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/deleteDiagnosi/{idDiagnosi}")
    public ResponseEntity deleteDiagnosi(@PathVariable String idDiagnosi) {
        Boolean b = diagnosiService.deleteDiagnosi(idDiagnosi);
        if (b) {
            return ResponseEntity.ok("diagnosi cancellata");
        }
        return ResponseEntity.ofNullable("la diagnosi non esiste");
    }

    @GetMapping("/getDiagnosi/{idDiagnosi}")
    public ResponseEntity getDiagnosi(@PathVariable String idDiagnosi) {
        DiagnosiResponse diagnosiResponse = diagnosiService.getDiagnosi(idDiagnosi);
        if (diagnosiResponse != null) {
            return ResponseEntity.ok(diagnosiResponse);
        }
        return ResponseEntity.notFound().build();
    }

}
