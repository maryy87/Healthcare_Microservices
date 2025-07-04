package com.example.Patient.service;

import com.example.Patient.config.AppuntamentoClientConfig;
import com.example.Patient.config.DoctorClientConfig;
import com.example.Patient.dto.request.AppuntamentoRequest;
import com.example.Patient.dto.request.FiltroPatientRequest;
import com.example.Patient.dto.request.PatientRequest;
import com.example.Patient.dto.response.AppuntamentoResponse;
import com.example.Patient.dto.response.AppuntamentoResponse1;
import com.example.Patient.dto.response.DoctorResponse;
import com.example.Patient.dto.response.PatientResponse;
import com.example.Patient.mapper.PatientMapper;
import com.example.Patient.model.Patient;
import com.example.Patient.repository.CustomPatientRepository;
import com.example.Patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatientService {

    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private CustomPatientRepository customPatientRepository;
    @Autowired
    private AppuntamentoClientConfig appuntamentoClientConfig;
    @Autowired
    private DoctorClientConfig doctorClientConfig;

    private final KafkaTemplate<String, AppuntamentoResponse1> kafkaTemplate ;

    @Autowired
    public PatientService(KafkaTemplate<String, AppuntamentoResponse1> kafkaTemplate,PatientRepository patientRepository, PatientMapper patientMapper,CustomPatientRepository customPatientRepository,DoctorClientConfig doctorClientConfig,AppuntamentoClientConfig appuntamentoClientConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.customPatientRepository=customPatientRepository;
        this.doctorClientConfig=doctorClientConfig;
        this.appuntamentoClientConfig=appuntamentoClientConfig;

    }


    public AppuntamentoResponse1 creaAppuntamento(AppuntamentoRequest appuntamentoRequest) {
        if (appuntamentoRequest != null) {

            DoctorResponse doctorResponse = doctorClientConfig.getDoctor(appuntamentoRequest.getIdDoctor()).getBody();

            PatientResponse patientResponse = getPatient(appuntamentoRequest.getIdPaziente());

            AppuntamentoResponse appuntamentoResponse = appuntamentoClientConfig.postSaveAppunt(appuntamentoRequest);

            AppuntamentoResponse1 appuntamentoResponse1 = new AppuntamentoResponse1();

            appuntamentoResponse1.setIdAppuntamento(appuntamentoResponse.getIdAppuntamento());
            appuntamentoResponse1.setDataAppuntamento(appuntamentoResponse.getDataAppuntamento());
            appuntamentoResponse1.setLuogoAppuntamento(appuntamentoResponse.getLuogoAppuntamento());
            appuntamentoResponse1.setStatoAppuntamento(appuntamentoResponse.getStatoAppuntamento());

            assert doctorResponse != null;
            appuntamentoResponse1.setCognomeDoctor(doctorResponse.getCognome());
            appuntamentoResponse1.setNomeDoctor(doctorResponse.getNome());
            appuntamentoResponse1.setEmailDoctor(doctorResponse.getEmail());
            appuntamentoResponse1.setData_nascitaDoctor(doctorResponse.getData_nascita());
            appuntamentoResponse1.setSpecializzazione(doctorResponse.getSpecializzazione());

            assert patientResponse != null;
            appuntamentoResponse1.setCognomePaziente(patientResponse.getCognome());
            appuntamentoResponse1.setNomePaziente(patientResponse.getNome());
            appuntamentoResponse1.setData_nascitaPaziente(patientResponse.getData_nascita());
            appuntamentoResponse1.setEmailPaziente(patientResponse.getEmail());
            appuntamentoResponse1.setNumero_telefonoPaziente(patientResponse.getNumero_telefono());

//            ###aggiunto kafka per mandare un messagio a dottore quando un appuntamento è creato
            kafkaTemplate.send("new-appuntamento", appuntamentoResponse1);
            log.info("topic inviato a kafka");

            return appuntamentoResponse1;

        }
        return null;
    }


    public List<PatientResponse> ricercaPatient(FiltroPatientRequest filtroPatientRequest) {
        if (filtroPatientRequest != null) {
            List<Patient> patientFiltro = customPatientRepository.findPatientFiltro(filtroPatientRequest);
            List<PatientResponse> patientResponses = patientMapper.mapPatientListToResponse(patientFiltro);
            return patientResponses;
        }
        return null;
    }

    public PatientResponse savePatient(PatientRequest patientRequest) {

        Patient patient = patientRepository.save(patientMapper.mapPatientRequestToEntity(patientRequest));
        return patientMapper.mapPatientEntityToResponse(patient);
    }

    public PatientResponse updatePatient(PatientRequest patientRequest, Long idPatient) {

        if (patientRepository.findById(idPatient).isPresent()) {
            Patient patient = patientMapper.mapPatientRequestToEntity(patientRequest);
            patient.setIdPatient(idPatient);
            Patient patient1 = patientRepository.save(patient);
            return patientMapper.mapPatientEntityToResponse(patient1);
        }
        return null;
    }

    public Boolean deletePatient(Long idPatient) {
        if (patientRepository.findById(idPatient).isPresent()) {
            patientRepository.deleteById(idPatient);
            if (patientRepository.findById(idPatient).isPresent()) {
                return false;
            }
            return true;
        }
        return false;

    }

    public PatientResponse getPatient(Long idPatient) {
        Optional<Patient> optionalPatient = patientRepository.findById(idPatient);
        if (optionalPatient.isPresent()) {
            return patientMapper.mapPatientEntityToResponse(optionalPatient.get());
        }
        return null;

    }
}
