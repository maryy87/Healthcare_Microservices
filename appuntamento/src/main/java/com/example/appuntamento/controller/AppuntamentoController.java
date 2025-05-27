package com.example.appuntamento.controller;

import com.example.appuntamento.dto.request.AppuntamentoRequest;
import com.example.appuntamento.dto.response.AppuntamentoResponse;
import com.example.appuntamento.service.AppuntamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/appuntamento")
public class AppuntamentoController {

    @Autowired
    private AppuntamentoService appuntamentoService;

    @PostMapping("/postSaveAppunt")
    public ResponseEntity postSaveAppunt(@RequestBody AppuntamentoRequest appuntamentoRequest){
        log.info("entrato dentro appuntamento controller");
        AppuntamentoResponse appuntamentoResponse=appuntamentoService.postSaveAppuntamento(appuntamentoRequest);
        if (appuntamentoResponse != null) {
            return ResponseEntity.ok(appuntamentoResponse);
        }
        return ResponseEntity.notFound().build();
    }
}
