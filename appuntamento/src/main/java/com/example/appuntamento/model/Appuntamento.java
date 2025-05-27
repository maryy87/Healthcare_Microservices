package com.example.appuntamento.model;

import com.example.appuntamento.utils.Stato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "appuntamento")
public class Appuntamento {

    @Id
    private String idAppuntamento;

    private Long idPaziente;
    private Long idDoctor;
    private LocalDateTime dataAppuntamento;
    private Stato statoAppuntamento;
    private String luogoAppuntamento;
    private LocalDateTime dataCreazioneAppuntamento;

}
