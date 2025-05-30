package com.example.MedicalRecord.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RisultatiEsameLaboratorioRequest {

    private String tipoEsame;                // Es: "Analisi del sangue"
    private String esito;                    // Es: "Valori nella norma"
    private LocalDate dataEsecuzione;
}
