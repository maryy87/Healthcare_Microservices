package com.example.MedicalRecord.controller;

import com.example.MedicalRecord.dto.request.RisultatiEsameLaboratorioRequest;
import com.example.MedicalRecord.dto.response.RisultatiEsameLaboratorioResponse;
import com.example.MedicalRecord.service.RisultatiEsameLaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/risultatiEsameLaboratorio")
public class RisultatiEsameLaboratorioController {

    @Autowired
    private RisultatiEsameLaboratorioService risultatiEsameLaboratorioService;

    @PostMapping("/postSaveRisultatiEsameLaboratorio")
    public ResponseEntity postSaveRisultatiEsameLaboratorio(@RequestBody RisultatiEsameLaboratorioRequest risultatiEsameLaboratorioRequest) {
        RisultatiEsameLaboratorioResponse risultatiEsameLaboratorioResponse = risultatiEsameLaboratorioService.postSaveRisultatiEsameLaboratorio(risultatiEsameLaboratorioRequest);
        if (risultatiEsameLaboratorioResponse != null) {
            return ResponseEntity.ok(risultatiEsameLaboratorioResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/updateRisultatiEsameLaboratorio/{idRisultatiEsameLaboratorio}")
    public ResponseEntity updateRisultatiEsameLaboratorio(@RequestBody RisultatiEsameLaboratorioRequest risultatiEsameLaboratorioRequest, @PathVariable String idRisultatiEsameLaboratorio) {
        RisultatiEsameLaboratorioResponse risultatiEsameLaboratorioResponse = risultatiEsameLaboratorioService.updateRisultatiEsameLaboratorio(risultatiEsameLaboratorioRequest, idRisultatiEsameLaboratorio);
        if (risultatiEsameLaboratorioResponse != null) {
            return ResponseEntity.ok(risultatiEsameLaboratorioResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/deleteRisultatiEsameLaboratorio/{idRisultatiEsameLaboratorio}")
    public ResponseEntity deleteRisultatiEsameLaboratorio(@PathVariable String idRisultatiEsameLaboratorio) {
        Boolean b = risultatiEsameLaboratorioService.deleteRisultatiEsameLaboratorio(idRisultatiEsameLaboratorio);
        if (b) {
            return ResponseEntity.ok("RisultatiEsameLaboratorio cancellata");
        }
        return ResponseEntity.ofNullable("RisultatiEsameLaboratorio non esiste");
    }

    @GetMapping("/getRisultatiEsameLaboratorio/{idRisultatiEsameLaboratorio}")
    public ResponseEntity getRisultatiEsameLaboratorio(@PathVariable String idRisultatiEsameLaboratorio) {
        RisultatiEsameLaboratorioResponse risultatiEsameLaboratorioResponse = risultatiEsameLaboratorioService.getRisultatiEsameLaboratorio(idRisultatiEsameLaboratorio);
        if (risultatiEsameLaboratorioResponse != null) {
            return ResponseEntity.ok(risultatiEsameLaboratorioResponse);
        }
        return ResponseEntity.notFound().build();
    }

}
