package com.example.MedicalRecord.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescrizioniRequest {

    private String idPaziente;
    private String nomeFarmaco;                     // Nome del farmaco
    private String dosaggio;                    // Es. "500mg", "10ml"
    private String frequenzaSomministrazione;   // Es. "2 volte al giorno"
    private String durataTrattamento;           // Es. "7 giorni"
    private String viaSomministrazione;         // Es. "Orale", "Intramuscolare", "Endovenosa"
    private String idMedico;

}
