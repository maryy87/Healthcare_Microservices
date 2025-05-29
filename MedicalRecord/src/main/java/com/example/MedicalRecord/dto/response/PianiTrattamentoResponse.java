package com.example.MedicalRecord.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PianiTrattamentoResponse {

    private String idPianiTrattamento;
    private Long idPaziente;                  // ID univoco del paziente
    private String obiettivoTrattamento;        // Es: "Controllare la pressione sanguigna"
    private String durataStimata;               // Es: "3 mesi", "6 settimane"
    private Long idMedicoResponsabile;          // ID o nome del medico che ha redatto il piano
    private LocalDate dataInizioTrattamento;    // Es: 2025-06-01
    private LocalDate dataFineTrattamento;

}
