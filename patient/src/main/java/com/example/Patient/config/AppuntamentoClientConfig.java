package com.example.Patient.config;

import com.example.Patient.dto.request.AppuntamentoRequest;
import com.example.Patient.dto.response.AppuntamentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "appuntamentoService", url = "http://localhost:8084/api/appuntamento")
public interface AppuntamentoClientConfig {

    @PostMapping("/postSaveAppunt")
    AppuntamentoResponse postSaveAppunt(@RequestBody AppuntamentoRequest appuntamentoRequest);
}
