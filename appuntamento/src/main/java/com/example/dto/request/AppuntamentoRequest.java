package com.example.dto.request;

import com.example.dto.response.AppuntamentoResponse;
import com.example.utils.Stato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppuntamentoRequest {

    private Long idPaziente;
    private Long idDoctor;
    private LocalDateTime dataAppuntamento;
    private Stato statoAppuntamento;
    private String luogoAppuntamento;
    private LocalDateTime dataCreazioneAppuntamento;

}
