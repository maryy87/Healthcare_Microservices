package com.example.Patient;

import com.example.Patient.controller.PatientController;
import com.example.Patient.dto.request.AppuntamentoRequest;
import com.example.Patient.dto.request.PatientRequest;
import com.example.Patient.dto.response.AppuntamentoResponse1;
import com.example.Patient.dto.response.PatientResponse;
import com.example.Patient.service.PatientService;
import com.example.Patient.utils.Stato;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @MockBean
    private PatientService patientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Converte oggetti Java in JSON e viceversa

    @Test
    void savePatientTest() throws Exception {
        PatientRequest request = new PatientRequest();
        request.setNome("Mario");
        request.setCognome("Rossi");
        request.setEmail("mario.rossi@example.com");
        request.setData_nascita(LocalDate.of(1985, 3, 15));
        request.setCodicefiscale("RSSMRA85C15H501Z");
        request.setGenere("Maschio");
        request.setNumero_telefono("+393331112233");
        request.setIndrizzo("Via Roma 10, Milano");
        request.setAllergies(Arrays.asList("Polline", "Penicillina"));

        PatientResponse response = new PatientResponse();
        response.setIdPatient(1L);
        response.setNome("Mario");
        response.setCognome("Rossi");
        response.setEmail("mario.rossi@example.com");
        response.setData_nascita(LocalDate.of(1985, 3, 15));
        response.setCodicefiscale("RSSMRA85C15H501Z");
        response.setGenere("Maschio");
        response.setNumero_telefono("+393331112233");
        response.setIndrizzo("Via Roma 10, Milano");
        response.setAllergies(Arrays.asList("Polline", "Penicillina"));

        // 3. Definisci il comportamento del mock del service
        when(patientService.savePatient(request)).thenReturn(response);

        // 4. Esegui la chiamata POST e verifica il risultato
        mockMvc.perform(post("/patient/savePatient") // Endpoint da testare
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))) // Il JSON vero e proprio
                .andExpect(status().isOk()) // Deve restituire 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idPatient").value(1L))
                .andExpect(jsonPath("$.nome").value("Mario"))
                .andExpect(jsonPath("$.email").value("mario.rossi@example.com"));

    }

    @Test
    void testSavePatient_notFound() throws Exception {
        // 1. Input valido ma il service restituisce null
        PatientRequest request = new PatientRequest();
        request.setNome("Mario");

        when(patientService.savePatient(request)).thenReturn(null); // Simula fallimento

        mockMvc.perform(post("/patient/savePatient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound()); // Deve restituire 404
    }

    @Test
    void updatePatientTest() throws Exception {

        Long id = 12L;

        PatientRequest request = new PatientRequest();
        request.setNome("Mario");
        request.setCognome("Rossi");
        request.setEmail("mario.rossi@example.com");
        request.setData_nascita(LocalDate.of(1985, 3, 15));
        request.setCodicefiscale("RSSMRA85C15H501Z");
        request.setGenere("Maschio");
        request.setNumero_telefono("+393331112233");
        request.setIndrizzo("Via Roma 10, Milano");
        request.setAllergies(Arrays.asList("Polline", "Penicillina"));

        PatientResponse response = new PatientResponse();
        response.setIdPatient(id);
        response.setNome("Mario");
        response.setCognome("Rossi");
        response.setEmail("mario.rossi@example.com");
        response.setData_nascita(LocalDate.of(1985, 3, 15));
        response.setCodicefiscale("RSSMRA85C15H501Z");
        response.setGenere("Maschio");
        response.setNumero_telefono("+393331112233");
        response.setIndrizzo("Via Roma 10, Milano");
        response.setAllergies(Arrays.asList("Polline", "Penicillina"));

        when(patientService.updatePatient(request, id)).thenReturn(response);


        // 4. Esegui la chiamata POST e verifica il risultato
        mockMvc.perform(put("/patient/updatePatient/{idPatient}", id) // Endpoint da testare
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))) // Il JSON vero e proprio
                .andExpect(status().isOk()) // Deve restituire 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idPatient").value(12L))
                .andExpect(jsonPath("$.nome").value("Mario"))
                .andExpect(jsonPath("$.email").value("mario.rossi@example.com"));
    }

    @Test
    void testUpdatePatient_notFound() throws Exception {

        Long id = 12L;
        // 1. Input valido ma il service restituisce null
        PatientRequest request = new PatientRequest();
        request.setNome("Mario");

        when(patientService.updatePatient(request, id)).thenReturn(null); // Simula fallimento

        mockMvc.perform(put("/patient/updatePatient/{idPatient}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound()); // Deve restituire 404
    }

    @Test
    void deletePatientTest() throws Exception {
        Long id = 12L;

        when(patientService.deletePatient(id)).thenReturn(true);

        mockMvc.perform(delete("/patient/deletePatient/{idPatient}", id))
                .andExpect(status().isOk());
        ;// Deve restituire 200 OK

    }

    @Test
    void deletePatientTestWithPatientDoes_notExist() throws Exception {
        Long id = 12L;

        when(patientService.deletePatient(id)).thenReturn(false);

        mockMvc.perform(delete("/patient/deletePatient/{idPatient}", id))
                .andExpect(status().isOk());
        ;// Deve restituire 200 OK

    }

    @Test
    void getPatientTest() throws Exception {
        Long id = 12L;


        PatientResponse response = new PatientResponse();
        response.setIdPatient(id);
        response.setNome("Mario");
        response.setCognome("Rossi");
        response.setEmail("mario.rossi@example.com");
        response.setData_nascita(LocalDate.of(1985, 3, 15));
        response.setCodicefiscale("RSSMRA85C15H501Z");
        response.setGenere("Maschio");
        response.setNumero_telefono("+393331112233");
        response.setIndrizzo("Via Roma 10, Milano");
        response.setAllergies(Arrays.asList("Polline", "Penicillina"));

        when(patientService.getPatient(id)).thenReturn(response);

        mockMvc.perform(get("/patient/getPatient/{idPatient}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPatient").value(12L));// Deve restituire 200 OK
    }

    @Test
    void getPatientTestWithPatientDoes_notExist() throws Exception {
        Long id = 12L;

        when(patientService.getPatient(id)).thenReturn(null);

        mockMvc.perform(get("/patient/getPatient/{idPatient}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void creaAppuntamentoTest() throws Exception {

        Stato stato = Stato.COMPLETATO;

        AppuntamentoRequest appuntamentoRequest = new AppuntamentoRequest();
        appuntamentoRequest.setIdPaziente(12L);
        appuntamentoRequest.setIdDoctor(10L);
        appuntamentoRequest.setDataAppuntamento(LocalDateTime.of(2025, 7, 15, 14, 30));
        appuntamentoRequest.setStatoAppuntamento(stato); // supponiamo che sia un enum
        appuntamentoRequest.setLuogoAppuntamento("Studio medico - Milano");


        AppuntamentoResponse1 appuntamento = new AppuntamentoResponse1();
        appuntamento.setIdAppuntamento("APT001");
        appuntamento.setNomePaziente("Luca");
        appuntamento.setCognomePaziente("Verdi");
        appuntamento.setEmailPaziente("luca.verdi@example.com");
        appuntamento.setData_nascitaPaziente(LocalDate.of(1990, 2, 14));
        appuntamento.setNumero_telefonoPaziente("+393471112233");
        appuntamento.setNomeDoctor("Francesca");
        appuntamento.setCognomeDoctor("Neri");
        appuntamento.setEmailDoctor("francesca.neri@ospedale.it");
        appuntamento.setData_nascitaDoctor(LocalDate.of(1980, 6, 8));
        appuntamento.setSpecializzazione("Dermatologia");
        appuntamento.setDataAppuntamento(LocalDateTime.of(2025, 7, 15, 9, 0));
        appuntamento.setStatoAppuntamento(stato);  // Assicurati che enum Stato sia definito
        appuntamento.setLuogoAppuntamento("Ambulatorio 3 - Ospedale San Carlo, Milano");


        when(patientService.creaAppuntamento(appuntamentoRequest)).thenReturn(appuntamento);

        // 4. Esegui la chiamata POST e verifica il risultato
        mockMvc.perform(post("/patient/creaAppuntamento") // Endpoint da testare
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appuntamentoRequest))) // Il JSON vero e proprio
                .andExpect(status().isOk()) // Deve restituire 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nomePaziente").value("Luca"))
                .andExpect(jsonPath("$.emailPaziente").value("luca.verdi@example.com"));
    }

    @Test
    void creaAppuntamentoTestwithApp_doesNotExist() throws Exception {

        Stato stato = Stato.COMPLETATO;

        AppuntamentoRequest appuntamentoRequest = new AppuntamentoRequest();
        appuntamentoRequest.setIdPaziente(12L);

        when(patientService.creaAppuntamento(appuntamentoRequest)).thenReturn(null);

        mockMvc.perform(post("/patient/creaAppuntamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appuntamentoRequest)))
                .andExpect(status().isNotFound());
    }
}
