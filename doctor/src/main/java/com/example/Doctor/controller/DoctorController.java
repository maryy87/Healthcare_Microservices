package com.example.Doctor.controller;

import com.example.Doctor.dto.request.DoctorRequest;
import com.example.Doctor.dto.request.FiltroDoctorRequest;
import com.example.Doctor.dto.response.DoctorResponse;
import com.example.Doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {


    @Autowired
    DoctorService doctorService;


    @PostMapping("/ricercaDoctorFitro")
    public ResponseEntity ricercaDoctor(@RequestBody FiltroDoctorRequest filtroDoctorRequest) {

        List<DoctorResponse> doctorResponseList= doctorService.ricercaDoctor(filtroDoctorRequest);
        if (doctorResponseList != null) {
            return ResponseEntity.ok(doctorResponseList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/saveDoctor",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorResponse> saveDoctor(@RequestBody DoctorRequest doctorRequest) {
        DoctorResponse doctorResponse = doctorService.saveDoctor(doctorRequest);
        if (doctorResponse != null) {
            return ResponseEntity.ok(doctorResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/updateDoctor/{idDoctor}")
    public  ResponseEntity<DoctorResponse>  updateDoctor(@RequestBody DoctorRequest doctorRequest, @PathVariable Long idDoctor) {

        DoctorResponse doctorResponse = doctorService.updateDoctor(doctorRequest, idDoctor);
        if (doctorResponse != null) {
            return ResponseEntity.ok(doctorResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteDoctor/{idDoctor}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long idDoctor) {
        boolean b = doctorService.deleteDoctor(idDoctor);
        if (b) {
            return ResponseEntity.ok("dottore eliminato con successo!");
        }
        return ResponseEntity.ofNullable("il dottore non esiste!");
    }

    @GetMapping("/getDoctor/{idDoctor}")
    public  ResponseEntity<DoctorResponse>  getDoctor(@PathVariable Long idDoctor) {
        DoctorResponse doctorResponse = doctorService.getDoctor(idDoctor);
        if (doctorResponse != null) {
            return ResponseEntity.ok(doctorResponse);
        }
        return ResponseEntity.notFound().build();

    }
}
