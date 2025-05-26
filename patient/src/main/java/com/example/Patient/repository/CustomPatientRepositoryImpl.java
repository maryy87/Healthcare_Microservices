package com.example.Patient.repository;

import com.example.Patient.dto.request.FiltroPatientRequest;
import com.example.Patient.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomPatientRepositoryImpl implements CustomPatientRepository{

    final EntityManager entityManager;

    public CustomPatientRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Patient> findPatientFiltro(FiltroPatientRequest filtroPatientRequest) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
        Root<Patient> patientRoot = cq.from(Patient.class);
        List<Predicate> predicateList = new ArrayList<>();


        if (filtroPatientRequest.getNome() != null && !filtroPatientRequest.getNome().isEmpty()) {
            Predicate predicate = cb.like(patientRoot.get("nome"), "%" + filtroPatientRequest.getNome() + "%");
            predicateList.add(predicate);
        }
        if (filtroPatientRequest.getEmail() != null && !filtroPatientRequest.getEmail().isEmpty()) {
            Predicate predicate = cb.like(patientRoot.get("email"), "%" + filtroPatientRequest.getEmail() + "%");
            predicateList.add(predicate);
        }
        if (filtroPatientRequest.getCognome()!= null && !filtroPatientRequest.getCognome().isEmpty()) {
            Predicate predicate = cb.like(patientRoot.get("cognome"), "%" + filtroPatientRequest.getCognome()+ "%");
            predicateList.add(predicate);
        }
        if (filtroPatientRequest.getCodicefiscale() != null && !filtroPatientRequest.getCodicefiscale().isEmpty()) {
            Predicate predicate = cb.like(patientRoot.get("codicefiscale"), "%" + filtroPatientRequest.getCodicefiscale() + "%");
            predicateList.add(predicate);
        }
        if (filtroPatientRequest.getGenere()!= null && !filtroPatientRequest.getGenere().isEmpty()) {
            Predicate predicate = cb.like(patientRoot.get("genere"), "%" + filtroPatientRequest.getGenere()+ "%");
            predicateList.add(predicate);
        }
        if (filtroPatientRequest.getIndrizzo() != null && !filtroPatientRequest.getIndrizzo().isEmpty()) {
            Predicate predicate = cb.like(patientRoot.get("indrizzo"), "%" + filtroPatientRequest.getIndrizzo() + "%");
            predicateList.add(predicate);
        }
        if (filtroPatientRequest.getNumero_telefono()!= null && !filtroPatientRequest.getNumero_telefono().isEmpty()) {
            Predicate predicate = cb.like(patientRoot.get("numero_telefono"), "%" + filtroPatientRequest.getNumero_telefono() + "%");
            predicateList.add(predicate);
        }
        if (filtroPatientRequest.getAllergies()!= null && !filtroPatientRequest.getAllergies().isEmpty()) {
            Predicate predicate = cb.like(patientRoot.get("allergies"), "%" + filtroPatientRequest.getAllergies() + "%");
            predicateList.add(predicate);
        }
        if (filtroPatientRequest.getData_nascita()!= null ) {
            Predicate predicate = cb.equal(patientRoot.get("data_nascita"),  filtroPatientRequest.getData_nascita() );
            predicateList.add(predicate);
        }
        if (filtroPatientRequest.getData_nascita()!= null ) {
            Predicate predicate = cb.lessThanOrEqualTo(patientRoot.get("data_nascita"),  filtroPatientRequest.getData_nascita() );
            predicateList.add(predicate);
        }
        if (filtroPatientRequest.getData_nascita()!= null ) {
            Predicate predicate = cb.greaterThanOrEqualTo(patientRoot.get("data_nascita"),  filtroPatientRequest.getData_nascita() );
            predicateList.add(predicate);
        }



        cq.where(predicateList.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
