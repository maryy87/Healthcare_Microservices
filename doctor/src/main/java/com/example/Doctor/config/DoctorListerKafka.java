package com.example.Doctor.config;

import com.example.Doctor.dto.response.AppuntamentoResponse1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DoctorListerKafka {

    @KafkaListener(topics = "new-appuntamento", groupId = "appuntamento-group")
    public void kafkaConsumer(AppuntamentoResponse1 appuntamento) {
        log.info("topic inviato a kafka");
        System.out.println("✔️ Ricevuto in PatientController: " + "luogo appuntamento " + appuntamento.getLuogoAppuntamento()+" data appuntamento " +  appuntamento.getDataAppuntamento());
    }
}
