package com.example.Doctor.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {

    private String nome;

    private String cognome;

    private String email;

    private LocalDate data_nascita;

    private String codicefiscale;

    private String genere;

    private String numero_telefono;

    private String licenseNumber;

    private String specializzazione;

}
