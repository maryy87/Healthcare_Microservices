package com.example.dto.response;

import com.example.utils.Stato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppuntamentoResponse {

    private Long idAppuntamento;

    private Long idPaziente;
    private Long idDoctor;
    private LocalDateTime dataAppuntamento;
    private Stato statoAppuntamento;
    private String luogoAppuntamento;
    private LocalDateTime dataCreazioneAppuntamento;
}
