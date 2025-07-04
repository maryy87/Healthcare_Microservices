package com.example.Patient;

import com.example.Patient.config.AppuntamentoClientConfig;
import com.example.Patient.config.DoctorClientConfig;
import com.example.Patient.dto.request.AppuntamentoRequest;
import com.example.Patient.dto.request.FiltroPatientRequest;
import com.example.Patient.dto.request.PatientRequest;
import com.example.Patient.dto.response.AppuntamentoResponse;
import com.example.Patient.dto.response.AppuntamentoResponse1;
import com.example.Patient.dto.response.DoctorResponse;
import com.example.Patient.dto.response.PatientResponse;
import com.example.Patient.mapper.PatientMapper;
import com.example.Patient.model.Patient;
import com.example.Patient.repository.CustomPatientRepository;
import com.example.Patient.repository.PatientRepository;
import com.example.Patient.service.PatientService;
import com.example.Patient.utils.Stato;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {


    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientService patientService;

    @Mock
    private CustomPatientRepository customPatientRepository;

    @Mock
    private DoctorClientConfig doctorClientConfig;

    @Mock
    private AppuntamentoClientConfig appuntamentoClientConfig;

    @Mock
    private KafkaTemplate kafkaTemplate;


    @Test
    void testSavePatient() {
        // 1. Oggetti di test
        PatientRequest request = new PatientRequest();
        request.setNome("Mario Rossi");

        Patient patientEntity = new Patient();
        patientEntity.setIdPatient(1L);
        patientEntity.setNome("Mario Rossi");

        PatientResponse response = new PatientResponse();
        response.setIdPatient(1L);
        response.setNome("Mario Rossi");

        // 2. Definizione comportamento dei mock
        Mockito.when(patientMapper.mapPatientRequestToEntity(request))
                .thenReturn(patientEntity);

        Mockito.when(patientRepository.save(patientEntity))
                .thenReturn(patientEntity);

        Mockito.when(patientMapper.mapPatientEntityToResponse(patientEntity))
                .thenReturn(response);

        // 3. Chiamata al metodo da testare
        PatientResponse result = patientService.savePatient(request);

        // 4. Verifiche
        assertNotNull(result);
        assertEquals(1L, result.getIdPatient());
        assertEquals("Mario Rossi", result.getNome());

        // 5. Verifica che i mock siano stati chiamati
        Mockito.verify(patientMapper).mapPatientRequestToEntity(request);
        Mockito.verify(patientRepository).save(patientEntity);
        Mockito.verify(patientMapper).mapPatientEntityToResponse(patientEntity);
    }


    @Test
    void testUpdatePatient() {
        Long id = 9L;
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

        Patient patientMappato = new Patient();
        patientMappato.setNome("Mario");
        patientMappato.setCognome("Rossi");
        patientMappato.setEmail("mario.rossi@example.com");
        patientMappato.setData_nascita(LocalDate.of(1985, 3, 15));
        patientMappato.setCodicefiscale("RSSMRA85C15H501Z");
        patientMappato.setGenere("Maschio");
        patientMappato.setNumero_telefono("+393331112233");
        patientMappato.setIndrizzo("Via Roma 10, Milano");
        patientMappato.setAllergies(Arrays.asList("Polline", "Penicillina"));

        Patient savedPatient = new Patient();
        savedPatient.setIdPatient(id);
        savedPatient.setNome("Mario");
        savedPatient.setCognome("Rossi");
        savedPatient.setEmail("mario.rossi@example.com");
        savedPatient.setData_nascita(LocalDate.of(1985, 3, 15));
        savedPatient.setCodicefiscale("RSSMRA85C15H501Z");
        savedPatient.setGenere("Maschio");
        savedPatient.setNumero_telefono("+393331112233");
        savedPatient.setIndrizzo("Via Roma 10, Milano");
        savedPatient.setAllergies(Arrays.asList("Polline", "Penicillina"));

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setIdPatient(id);
        patientResponse.setNome("Mario");
        patientResponse.setCognome("Rossi");
        patientResponse.setEmail("mario.rossi@example.com");
        patientResponse.setData_nascita(LocalDate.of(1985, 3, 15));
        patientResponse.setCodicefiscale("RSSMRA85C15H501Z");
        patientResponse.setGenere("Maschio");
        patientResponse.setNumero_telefono("+393331112233");
        patientResponse.setIndrizzo("Via Roma 10, Milano");
        patientResponse.setAllergies(Arrays.asList("Polline", "Penicillina"));

        Mockito.when(patientRepository.findById(id))
                .thenReturn(Optional.of(new Patient()))
                .thenReturn(Optional.empty());
        Mockito.when(patientMapper.mapPatientRequestToEntity(request)).thenReturn(patientMappato);
        Mockito.when(patientRepository.save(patientMappato)).thenReturn(savedPatient);
        Mockito.when(patientMapper.mapPatientEntityToResponse(savedPatient)).thenReturn(patientResponse);

        PatientResponse result = patientService.updatePatient(request, id);

        assertNotNull(result);
        assertEquals(id, result.getIdPatient());
        assertEquals("Mario", result.getNome());
        assertEquals("Rossi", result.getCognome());
        assertEquals("mario.rossi@example.com", result.getEmail());
        assertEquals(LocalDate.of(1985, 3, 15), result.getData_nascita());
        assertEquals("RSSMRA85C15H501Z", result.getCodicefiscale());
        assertEquals("Maschio", result.getGenere());
        assertEquals("+393331112233", result.getNumero_telefono());
        assertEquals("Via Roma 10, Milano", result.getIndrizzo());
        assertEquals(Arrays.asList("Polline", "Penicillina"), result.getAllergies());

        verify(patientRepository).findById(id);
        verify(patientMapper).mapPatientEntityToResponse(savedPatient);
        verify(patientMapper).mapPatientRequestToEntity(request);
        verify(patientRepository).save(patientMappato)
        ;

    }

    @Test
    void testUpdatePatient_whenPatientDoesNotExist() {
        // Arrange
        Long id = 1L;
        PatientRequest request = new PatientRequest();

        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        PatientResponse result = patientService.updatePatient(request, id);

        // Assert
        assertNull(result);
        verify(patientRepository).findById(id);
        verifyNoMoreInteractions(patientMapper, patientRepository);
    }

    @Test
    void testDeletePatient() {

        Long id = 8L;
        Mockito.when(patientRepository.findById(id))
                .thenReturn(Optional.of(new Patient()))
                .thenReturn(Optional.empty());

        doNothing().when(patientRepository).deleteById(id);

        Boolean result = patientService.deletePatient(id);
        assertTrue(result);

        verify(patientRepository, times(2)).findById(id);
        verify(patientRepository).deleteById(id);

    }

    @Test
    void testGetPatient() {
        Long id = 1L;

        Patient savedPatient = new Patient();
        savedPatient.setIdPatient(id);
        savedPatient.setNome("Mario");
        savedPatient.setCognome("Rossi");
        savedPatient.setEmail("mario.rossi@example.com");
        savedPatient.setData_nascita(LocalDate.of(1985, 3, 15));
        savedPatient.setCodicefiscale("RSSMRA85C15H501Z");
        savedPatient.setGenere("Maschio");
        savedPatient.setNumero_telefono("+393331112233");
        savedPatient.setIndrizzo("Via Roma 10, Milano");
        savedPatient.setAllergies(Arrays.asList("Polline", "Penicillina"));

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setIdPatient(id);
        patientResponse.setNome("Mario");
        patientResponse.setCognome("Rossi");
        patientResponse.setEmail("mario.rossi@example.com");
        patientResponse.setData_nascita(LocalDate.of(1985, 3, 15));
        patientResponse.setCodicefiscale("RSSMRA85C15H501Z");
        patientResponse.setGenere("Maschio");
        patientResponse.setNumero_telefono("+393331112233");
        patientResponse.setIndrizzo("Via Roma 10, Milano");
        patientResponse.setAllergies(Arrays.asList("Polline", "Penicillina"));

        Mockito.when(patientRepository.findById(id)).thenReturn(Optional.of(savedPatient));
        Mockito.when(patientMapper.mapPatientEntityToResponse(savedPatient)).thenReturn(patientResponse);

        PatientResponse result = patientService.getPatient(id);

        assertEquals(id, result.getIdPatient());
        assertEquals("Mario", result.getNome());
        assertEquals("Rossi", result.getCognome());
        assertEquals("mario.rossi@example.com", result.getEmail());
        assertEquals(LocalDate.of(1985, 3, 15), result.getData_nascita());
        assertEquals("RSSMRA85C15H501Z", result.getCodicefiscale());
        assertEquals("Maschio", result.getGenere());
        assertEquals("+393331112233", result.getNumero_telefono());
        assertEquals("Via Roma 10, Milano", result.getIndrizzo());
        assertEquals(Arrays.asList("Polline", "Penicillina"), result.getAllergies());

        verify(patientRepository).findById(id);
        verify(patientMapper).mapPatientEntityToResponse(savedPatient);

    }

    @Test
    void testGetPatient_DoesnotExist() {
        Long id = 1L;

        Mockito.when(patientRepository.findById(id)).thenReturn(Optional.empty());

        PatientResponse result = patientService.getPatient(id);
        assertNull(result);

        verify(patientRepository).findById(id);

    }

    @Test
    void testRicercaPatientConFiltroNotNull() {

        List<Patient> patientList = new ArrayList<>();
        List<PatientResponse> patientResponseList = new ArrayList<>();

        FiltroPatientRequest filtroPatientRequest = new FiltroPatientRequest();
        filtroPatientRequest.setNome("Mario");
        filtroPatientRequest.setCognome("Rossi");
        filtroPatientRequest.setEmail("mario.rossi@example.com");
        filtroPatientRequest.setData_nascita(LocalDate.of(1985, 3, 15));
        filtroPatientRequest.setCodicefiscale("RSSMRA85C15H501Z");
        filtroPatientRequest.setGenere("Maschio");
        filtroPatientRequest.setNumero_telefono("+393331112233");
        filtroPatientRequest.setIndrizzo("Via Roma 10, Milano");
        filtroPatientRequest.setAllergies(Arrays.asList("Polline", "Penicillina"));

        Mockito.when(customPatientRepository.findPatientFiltro(filtroPatientRequest)).thenReturn((patientList));
        Mockito.when(patientMapper.mapPatientListToResponse(patientList)).thenReturn(patientResponseList);

        List<PatientResponse> result = patientService.ricercaPatient(filtroPatientRequest);


        assertNotNull(filtroPatientRequest);
        assertNotNull(result);

        verify(customPatientRepository).findPatientFiltro(filtroPatientRequest);
        verify(patientMapper).mapPatientListToResponse(patientList);
    }

    @Test
    void testRicercaPatientConFiltroNull() {

        List<PatientResponse> result = patientService.ricercaPatient(null);

        assertNull(result);
    }

    @Test
    void testCreaAppuntamento() {

        Stato stato = Stato.COMPLETATO;

        AppuntamentoRequest appuntamentoRequest = new AppuntamentoRequest();
        appuntamentoRequest.setIdPaziente(12L);
        appuntamentoRequest.setIdDoctor(10L);
        appuntamentoRequest.setDataAppuntamento(LocalDateTime.of(2025, 7, 15, 14, 30));
        appuntamentoRequest.setStatoAppuntamento(stato); // supponiamo che sia un enum
        appuntamentoRequest.setLuogoAppuntamento("Studio medico - Milano");

        DoctorResponse doctor = new DoctorResponse();
        doctor.setIdDoctor(10L);
        doctor.setNome("Giulia");
        doctor.setCognome("Rossi");
        doctor.setEmail("giulia.rossi@example.com");
        doctor.setData_nascita(LocalDate.of(1980, 5, 22));
        doctor.setCodicefiscale("RSSGLI80E62H501U");
        doctor.setGenere("F");
        doctor.setNumero_telefono("+39 345 678 9012");
        doctor.setLicenseNumber("MED123456");
        doctor.setSpecializzazione("Cardiologia");

        Patient patient = new Patient();
        patient.setIdPatient(12L);
        patient.setNome("Mario");
        patient.setCognome("Rossi");
        patient.setEmail("mario.rossi@example.com");
        patient.setData_nascita(LocalDate.of(1985, 3, 15));
        patient.setCodicefiscale("RSSMRA85C15H501Z");
        patient.setGenere("Maschio");
        patient.setNumero_telefono("+393331112233");
        patient.setIndrizzo("Via Roma 10, Milano");
        patient.setAllergies(Arrays.asList("Polline", "Penicillina"));

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setIdPatient(12L);
        patientResponse.setNome("Mario");
        patientResponse.setCognome("Rossi");
        patientResponse.setEmail("mario.rossi@example.com");
        patientResponse.setData_nascita(LocalDate.of(1985, 3, 15));
        patientResponse.setCodicefiscale("RSSMRA85C15H501Z");
        patientResponse.setGenere("Maschio");
        patientResponse.setNumero_telefono("+393331112233");
        patientResponse.setIndrizzo("Via Roma 10, Milano");
        patientResponse.setAllergies(Arrays.asList("Polline", "Penicillina"));

        AppuntamentoResponse appuntamentoResponse = new AppuntamentoResponse();
        appuntamentoResponse.setIdAppuntamento("A12345");
        appuntamentoResponse.setIdPaziente(12L);
        appuntamentoResponse.setIdDoctor(10L);
        appuntamentoResponse.setDataAppuntamento(LocalDateTime.of(2025, 7, 10, 15, 30));
        Stato stato1 = Stato.ANNULATO;
        appuntamentoResponse.setStatoAppuntamento(stato1);
        appuntamentoResponse.setLuogoAppuntamento("Studio Medico Via Roma 12, Milano");
        appuntamentoResponse.setDataCreazioneAppuntamento(LocalDateTime.now());

        Mockito.when(doctorClientConfig.getDoctor(10L)).thenReturn(ResponseEntity.ok(doctor));
        Mockito.when(patientRepository.findById(12L)).thenReturn(Optional.of(patient));
        Mockito.when(patientService.getPatient(12L)).thenReturn(patientResponse);
        Mockito.when(appuntamentoClientConfig.postSaveAppunt(appuntamentoRequest)).thenReturn(appuntamentoResponse);
        // Mock Kafka (opzionale, ma consigliato per evitare errori)
        Mockito.when(kafkaTemplate.send(Mockito.anyString(), Mockito.any(AppuntamentoResponse1.class)))
                .thenReturn(null);

        AppuntamentoResponse1 result = patientService.creaAppuntamento(appuntamentoRequest);


        assertEquals("Mario", result.getNomePaziente());
        assertEquals("Giulia", result.getNomeDoctor());
        assertNotNull(appuntamentoRequest);
        assertNotNull(doctor);
        assertNotNull(patientResponse);
        assertNotNull(appuntamentoResponse);

    }

    @Test
    void testCreaAppuntamentoWithAppuntamentoNull() {
        AppuntamentoResponse1 result=patientService.creaAppuntamento(null);

        assertNull(result);



    }
}


























