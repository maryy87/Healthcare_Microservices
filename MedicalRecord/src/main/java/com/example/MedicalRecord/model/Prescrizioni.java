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
@Document(collection = "prescrizioni")
public class Prescrizioni {

    @Id
    private String idPrescrizione;
    private String nomeFarmaco;                     // Nome del farmaco
    private String dosaggio;                    // Es. "500mg", "10ml"
    private String frequenzaSomministrazione;   // Es. "2 volte al giorno"
    private String durataTrattamento;           // Es. "7 giorni"
    private String viaSomministrazione;         // Es. "Orale", "Intramuscolare", "Endovenosa"
    private LocalDate dataCreaPrescrizione;
}
