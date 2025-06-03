package com.example.Patient.dto.response;

import com.example.Patient.utils.Stato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppuntamentoResponse1 {


        private String idAppuntamento;

        private String nomePaziente;
        private String cognomePaziente;
        private String emailPaziente;
        private LocalDate data_nascitaPaziente;
        private String numero_telefonoPaziente;
        private String nomeDoctor;
        private String cognomeDoctor;
        private String emailDoctor;
        private LocalDate data_nascitaDoctor;
        private String specializzazione;
        private LocalDateTime dataAppuntamento;
        private Stato statoAppuntamento;
        private String luogoAppuntamento;
}
