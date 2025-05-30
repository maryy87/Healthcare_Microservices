package com.example.MedicalRecord.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "pianiTrattamento")
public class PianiTrattamento {

    @Id
    private String idPianiTrattamento;
    private String obiettivoTrattamento;        // Es: "Controllare la pressione sanguigna"
    private String durataStimata;               // Es: "3 mesi", "6 settimane"
    private LocalDate dataInizioTrattamento;    // Es: 2025-06-01
    private LocalDate dataFineTrattamento;      // (opzionale)

}
