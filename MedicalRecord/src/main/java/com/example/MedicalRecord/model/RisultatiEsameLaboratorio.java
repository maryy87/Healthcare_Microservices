package com.example.MedicalRecord.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "risultatiEsameLaboratorio")
public class RisultatiEsameLaboratorio {

    @Id
    private String idRisultatiEsameLaboratorio;
    private String tipoEsame;                // Es: "Analisi del sangue"
    private String esito;                    // Es: "Valori nella norma"
    private LocalDate dataEsecuzione;

}
