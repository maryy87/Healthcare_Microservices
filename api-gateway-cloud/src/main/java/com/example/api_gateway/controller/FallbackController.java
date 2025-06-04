package com.example.api_gateway.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/doctorFallBack")
    public String doctorFallBack() {
        return "Doctor Service is down!";
    }

    @GetMapping("/patientFallBack")
    public String patientFallBack() {
        return "Patient Service is down!";
    }

    @PostMapping("/appuntamentoFallBack")
    public String appuntamentoFallBack() {
        return "appuntamento service is down!";
    }

}