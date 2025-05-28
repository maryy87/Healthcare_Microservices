package com.example.MedicalRecord.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordResponse {

    private String idMedicalRecord;
    private LocalDateTime dataCreazioneCartellaMedica;
    private Long idPaziente;
    private List<String> diagnosiList;
    private List<String> elencoPrescrizioni;
    private List<String> pianiTrattamento;
    private List<String> risultatiEsameLaboratorio;
    private String anamnesi;

}
