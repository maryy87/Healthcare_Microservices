package com.example.MedicalRecord.dto.request;

import com.example.MedicalRecord.model.PianiTrattamento;
import com.example.MedicalRecord.model.Prescrizioni;
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
    private List<String> diagnosiList;
    private List<Prescrizioni> prescrizioni;
    private List<PianiTrattamento> pianiTrattamento;
    private List<String> risultatiEsameLaboratorio;
    private String anamnesi;
}
