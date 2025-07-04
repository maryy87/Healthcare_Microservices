package com.example.Patient;

import com.example.Patient.dto.request.PatientRequest;
import com.example.Patient.dto.response.PatientResponse;
import com.example.Patient.mapper.PatientMapper;
import com.example.Patient.model.Patient;
import com.example.Patient.repository.PatientRepository;
import com.example.Patient.service.PatientService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest1 {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientService patientService;

    @Test
    void testUpdatePatient_whenPatientExists() {
        // Arrange
        Long id = 1L;

        PatientRequest request = new PatientRequest();
        request.setNome("Mario Rossi");

        Patient mappedPatient = new Patient();
        mappedPatient.setNome("Mario Rossi");

        Patient savedPatient = new Patient();
        savedPatient.setIdPatient(id);
        savedPatient.setNome("Mario Rossi");

        PatientResponse expectedResponse = new PatientResponse();
        expectedResponse.setIdPatient(id);
        expectedResponse.setNome("Mario Rossi");

        when(patientRepository.findById(id)).thenReturn(Optional.of(new Patient()));
        when(patientMapper.mapPatientRequestToEntity(request)).thenReturn(mappedPatient);
        when(patientRepository.save(mappedPatient)).thenReturn(savedPatient);
        when(patientMapper.mapPatientEntityToResponse(savedPatient)).thenReturn(expectedResponse);

        // Act
        PatientResponse result = patientService.updatePatient(request, id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getIdPatient());
        assertEquals("Mario Rossi", result.getNome());

        verify(patientRepository).findById(id);
        verify(patientMapper).mapPatientRequestToEntity(request);
        verify(patientRepository).save(mappedPatient);
        verify(patientMapper).mapPatientEntityToResponse(savedPatient);
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
}


