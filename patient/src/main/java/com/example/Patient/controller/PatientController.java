package com.example.Patient.controller;

import com.example.Patient.dto.request.AppuntamentoRequest;
import com.example.Patient.dto.request.FiltroPatientRequest;
import com.example.Patient.dto.request.PatientRequest;
import com.example.Patient.dto.response.AppuntamentoResponse1;
import com.example.Patient.dto.response.PatientResponse;
import com.example.Patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;


    @PostMapping("/creaAppuntamento")
    public ResponseEntity creaAppuntamento(@RequestBody AppuntamentoRequest appuntamentoRequest) {
        AppuntamentoResponse1 appuntamentoResponse1=patientService.creaAppuntamento(appuntamentoRequest);
        if (appuntamentoResponse1 != null) {
            return ResponseEntity.ok("il paziente " + appuntamentoResponse1.getNomePaziente() + " " + appuntamentoResponse1.getCognomePaziente() +
                    " nato il " + appuntamentoResponse1.getData_nascitaPaziente().toString()+ "con il seguente nuumero di telefono "+ appuntamentoResponse1.getNumero_telefonoPaziente()+
                    "con il seguente email: "+ appuntamentoResponse1.getEmailPaziente() + " ha prenotato con il dottore " + appuntamentoResponse1.getNomeDoctor()
                    +" " + appuntamentoResponse1.getCognomeDoctor() + " sprcializzato in " + appuntamentoResponse1.getSpecializzazione() + " la data dell'appuntamento è fissato per il "+ appuntamentoResponse1.getDataAppuntamento() +
                    " a " + appuntamentoResponse1.getLuogoAppuntamento());

        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/ricercaPatientFiltro")
    public ResponseEntity ricercaPatient(@RequestBody FiltroPatientRequest filtroPatientRequest) {

        List<PatientResponse> patientResponseList=patientService.ricercaPatient(filtroPatientRequest);
        if (patientResponseList != null) {
           return ResponseEntity.ok(patientResponseList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/savePatient",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientResponse> savePatient(@RequestBody PatientRequest patientRequest) {
        PatientResponse patientResponse = patientService.savePatient(patientRequest);
        if (patientResponse != null) {
            return ResponseEntity.ok(patientResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/updatePatient/{idPatient}")
    public  ResponseEntity<PatientResponse>  updatePatient(@RequestBody PatientRequest patientRequest, @PathVariable Long idPatient) {

        PatientResponse patientResponse = patientService.updatePatient(patientRequest, idPatient);
        if (patientResponse != null) {
            return ResponseEntity.ok(patientResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletePatient/{idPatient}")
    public ResponseEntity<String> deletePatient(@PathVariable Long idPatient) {
        boolean b = patientService.deletePatient(idPatient);
        if (b) {
            return ResponseEntity.ok("paziente eliminato con successo!");
        }
        return ResponseEntity.ofNullable("il paziente non esiste!");
    }

    @GetMapping("/getPatient/{idPatient}")
    public  ResponseEntity<PatientResponse>  getPatient(@PathVariable Long idPatient) {
        PatientResponse patientResponse = patientService.getPatient(idPatient);
        if (patientResponse != null) {
            return ResponseEntity.ok(patientResponse);
        }
        return ResponseEntity.notFound().build();

    }

}
