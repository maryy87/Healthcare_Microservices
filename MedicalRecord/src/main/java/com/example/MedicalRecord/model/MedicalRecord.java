package com.example.MedicalRecord.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "medicalRecord")
public class MedicalRecord {

    @Id
    private String idMedicalRecord;

    private LocalDateTime dataCreazioneCartellaMedica;
    private Long idPaziente;
    private List<Diagnosi> diagnosiList;
    private List<Prescrizioni> prescrizioni;
    private List<PianiTrattamento> pianiTrattamento;
    private List<RisultatiEsameLaboratorio> risultatiEsameLaboratorio;
    private String anamnesi;

}
