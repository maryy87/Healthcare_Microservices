package com.example.MedicalRecord.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RisultatiEsameLaboratorioResponse {

    private String idRisultatiEsameLaboratorio;
    private String tipoEsame;                // Es: "Analisi del sangue"
    private String esito;                    // Es: "Valori nella norma"
    private LocalDate dataEsecuzione;

}
