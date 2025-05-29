package com.example.MedicalRecord.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescrizioniResponse {

    private String idPrescrizione;
    private Long idPaziente;
    private String nomeFarmaco;                     // Nome del farmaco
    private String dosaggio;                    // Es. "500mg", "10ml"
    private String frequenzaSomministrazione;   // Es. "2 volte al giorno"
    private String durataTrattamento;           // Es. "7 giorni"
    private String viaSomministrazione;         // Es. "Orale", "Intramuscolare", "Endovenosa"
    private LocalDate dataCreaPrescrizione;
    private Long idMedico;

}
