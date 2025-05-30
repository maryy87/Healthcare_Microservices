package com.example.MedicalRecord.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosiRequest {

    private String descrizione;              // Descrizione testuale della diagnosi
    private LocalDate dataDiagnosi;
}
