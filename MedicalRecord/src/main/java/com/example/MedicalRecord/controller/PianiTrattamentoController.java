package com.example.MedicalRecord.controller;

import com.example.MedicalRecord.dto.request.PianiTrattamentoRequest;
import com.example.MedicalRecord.dto.response.PianiTrattamentoResponse;
import com.example.MedicalRecord.service.PianiTrattamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/PianiTrattamento")
public class PianiTrattamentoController {

    @Autowired
    private PianiTrattamentoService pianiTrattamentoService;

    @PostMapping("/postSavePianiTrattamento")
    public ResponseEntity postSavePianiTrattamento(@RequestBody PianiTrattamentoRequest pianiTrattamentoRequest) {
        PianiTrattamentoResponse pianiTrattamentoResponse = pianiTrattamentoService.postSavePianiTrattamento(pianiTrattamentoRequest);
        if (pianiTrattamentoResponse != null) {
            return ResponseEntity.ok(pianiTrattamentoResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/updatePianiTrattamento/{idPianiTrattamento}")
    public ResponseEntity updatePianiTrattamento(@RequestBody PianiTrattamentoRequest pianiTrattamentoRequest, @PathVariable String idPianiTrattamento) {
        PianiTrattamentoResponse pianiTrattamentoResponse = pianiTrattamentoService.updatePianiTrattamento(pianiTrattamentoRequest, idPianiTrattamento);
        if (pianiTrattamentoResponse != null) {
            return ResponseEntity.ok(pianiTrattamentoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletePianiTrattamento/{idPianiTrattamento}")
    public ResponseEntity deletePianiTrattamento(@PathVariable String idPianiTrattamento) {
        Boolean b = pianiTrattamentoService.deletePianiTrattamento(idPianiTrattamento);
        if (b) {

            return ResponseEntity.ok("PianiTrattamento cancellato!");
        }
        return ResponseEntity.ofNullable("il PianiTrattamento non esiste!");
    }

    @GetMapping("/getMedicalRecord/{idPianiTrattamento}")
    public ResponseEntity getPianiTrattamento(@PathVariable String idPianiTrattamento) {
        PianiTrattamentoResponse pianiTrattamentoResponse = pianiTrattamentoService.getPianiTrattamento(idPianiTrattamento);
        if (pianiTrattamentoResponse != null) {
            return ResponseEntity.ok(pianiTrattamentoResponse);
        }
        return ResponseEntity.notFound().build();


    }

}
