package com.example.Patient.config;

import com.example.Patient.dto.response.DoctorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctorService", url = "http://localhost:8081/doctor")
public interface DoctorClientConfig {


    @GetMapping("/getDoctor/{idDoctor}")
    ResponseEntity<DoctorResponse> getDoctor(@PathVariable Long idDoctor);

    @Component
    public class DoctorClientFallback implements DoctorClientConfig {
        @Override
        public ResponseEntity<DoctorResponse> getDoctor(Long id) {
            throw new RuntimeException("Doctor service is down");
        }
    }
}
