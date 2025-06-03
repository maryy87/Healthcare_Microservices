package com.example.MedicalRecord.dto.request;

import com.example.MedicalRecord.model.Diagnosi;
import com.example.MedicalRecord.model.PianiTrattamento;
import com.example.MedicalRecord.model.Prescrizioni;
import com.example.MedicalRecord.model.RisultatiEsameLaboratorio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordRequest {


    private LocalDateTime dataCreazioneCartellaMedica;
    private Long idPaziente;
    private Diagnosi diagnosiList;
    private Prescrizioni prescrizioni;
    private PianiTrattamento pianiTrattamento;
    private RisultatiEsameLaboratorio risultatiEsameLaboratorio;
    private String anamnesi;
}
