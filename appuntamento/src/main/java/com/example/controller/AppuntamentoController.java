package com.example.controller;


import com.example.dto.request.AppuntamentoRequest;
import com.example.dto.response.AppuntamentoResponse;
import com.example.service.AppuntamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/appuntamento")
public class AppuntamentoController {

    @Autowired
    private AppuntamentoService appuntamentoService;

    @PostMapping("/postSaveAppunt")
    public ResponseEntity postSaveAppunt(@RequestBody AppuntamentoRequest appuntamentoRequest) {
        log.info("entrato dentro appuntamento controller");
        AppuntamentoResponse appuntamentoResponse = appuntamentoService.postSaveAppuntamento(appuntamentoRequest);
        if (appuntamentoResponse != null) {
            return ResponseEntity.ok(appuntamentoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/updateAppuntamento/{idAppuntamento}")
    public ResponseEntity updateAppuntamento(@RequestBody AppuntamentoRequest appuntamentoRequest, @PathVariable String idAppuntamento) {
        log.info("intrato nel metodo updateAppuntamento dentro appuntamentoController");
        AppuntamentoResponse appuntamentoResponse = appuntamentoService.updateAppuntamento(appuntamentoRequest, idAppuntamento);
        if (appuntamentoResponse != null) {
            return ResponseEntity.ok(appuntamentoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteAppuntamento/{idAppuntamento}")
    public ResponseEntity deleteAppuntamento(@PathVariable String idAppuntamento) {
        log.info("intrato nel metodo deleteAppuntamento dentro appuntamentoController");
        Boolean b = appuntamentoService.deleteAppuntamento(idAppuntamento);
        if (b) {
            return ResponseEntity.ok("appuntamento cancellato con successo!");
        }
        return ResponseEntity.ofNullable("l'appuntamento non esiste!");
    }

    @GetMapping("/getAppuntamento/{idAppuntamento}")
    public ResponseEntity getAppuntamento(@PathVariable String idAppuntamento) {
        AppuntamentoResponse appuntamentoResponse = appuntamentoService.getAppuntamento(idAppuntamento);
        if (appuntamentoResponse != null) {
            return ResponseEntity.ok(appuntamentoResponse);
        }
        return ResponseEntity.notFound().build();

    }
}
