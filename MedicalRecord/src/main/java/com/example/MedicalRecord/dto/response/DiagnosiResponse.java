package com.example.MedicalRecord.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosiResponse {

    private String idDiagnosi;               // Identificativo univoco della diagnosi
    private String descrizione;              // Descrizione testuale della diagnosi
    private LocalDate dataDiagnosi;

}
