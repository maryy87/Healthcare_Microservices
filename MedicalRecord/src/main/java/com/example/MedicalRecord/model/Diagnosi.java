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
@Document(collection = "diagnosi")
public class Diagnosi {

    @Id
    private String idDiagnosi;               // Identificativo univoco della diagnosi
    private String descrizione;              // Descrizione testuale della diagnosi
    private LocalDate dataDiagnosi;          // Data in cui la diagnosi è stata effettuata


}
