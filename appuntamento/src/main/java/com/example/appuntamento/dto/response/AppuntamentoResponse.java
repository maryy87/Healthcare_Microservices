package com.example.appuntamento.dto.response;

import com.example.appuntamento.utils.Stato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppuntamentoResponse {

    private String idAppuntamento;

    private Long idPaziente;
    private Long idDoctor;
    private LocalDateTime dataAppuntamento;
    private Stato statoAppuntamento;
    private String luogoAppuntamento;
    private LocalDateTime dataCreazioneAppuntamento;
}
