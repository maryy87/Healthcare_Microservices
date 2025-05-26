package com.example.Patient.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {

    private Long idPatient;

    private String nome;

    private String cognome;

    private String email;

    private LocalDate data_nascita;

    private String codicefiscale;

    private String genere;

    private String numero_telefono;

    private String indrizzo;

    private List<String> allergies;

}
