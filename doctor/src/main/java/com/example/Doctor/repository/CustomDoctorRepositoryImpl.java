package com.example.Doctor.repository;

import com.example.Doctor.dto.request.FiltroDoctorRequest;
import com.example.Doctor.model.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomDoctorRepositoryImpl implements CustomDoctorRepository {

    final EntityManager entityManager;

    public CustomDoctorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Doctor> findDoctorFiltro(FiltroDoctorRequest filtroDoctorRequest) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
        Root<Doctor> doctorRoot = cq.from(Doctor.class);
        List<Predicate> predicateList = new ArrayList<>();


        if (filtroDoctorRequest.getNome() != null && !filtroDoctorRequest.getNome().isEmpty()) {
            Predicate predicate = cb.like(doctorRoot.get("nome"), "%" + filtroDoctorRequest.getNome() + "%");
            predicateList.add(predicate);
        }
        if (filtroDoctorRequest.getEmail() != null && !filtroDoctorRequest.getEmail().isEmpty()) {
            Predicate predicate = cb.like(doctorRoot.get("email"), "%" + filtroDoctorRequest.getEmail() + "%");
            predicateList.add(predicate);
        }
        if (filtroDoctorRequest.getCognome()!= null && !filtroDoctorRequest.getCognome().isEmpty()) {
            Predicate predicate = cb.like(doctorRoot.get("cognome"), "%" + filtroDoctorRequest.getCognome()+ "%");
            predicateList.add(predicate);
        }
        if (filtroDoctorRequest.getCodicefiscale() != null && !filtroDoctorRequest.getCodicefiscale().isEmpty()) {
            Predicate predicate = cb.like(doctorRoot.get("codicefiscale"), "%" + filtroDoctorRequest.getCodicefiscale() + "%");
            predicateList.add(predicate);
        }
        if (filtroDoctorRequest.getGenere()!= null && !filtroDoctorRequest.getGenere().isEmpty()) {
            Predicate predicate = cb.like(doctorRoot.get("genere"), "%" + filtroDoctorRequest.getGenere()+ "%");
            predicateList.add(predicate);
        }
        if (filtroDoctorRequest.getLicenseNumber() != null && !filtroDoctorRequest.getLicenseNumber().isEmpty()) {
            Predicate predicate = cb.like(doctorRoot.get("licenseNumber"), "%" + filtroDoctorRequest.getLicenseNumber() + "%");
            predicateList.add(predicate);
        }
        if (filtroDoctorRequest.getNumero_telefono()!= null && !filtroDoctorRequest.getNumero_telefono().isEmpty()) {
            Predicate predicate = cb.like(doctorRoot.get("numero_telefono"), "%" + filtroDoctorRequest.getNumero_telefono() + "%");
            predicateList.add(predicate);
        }
        if (filtroDoctorRequest.getSpecializzazione()!= null && !filtroDoctorRequest.getSpecializzazione().isEmpty()) {
            Predicate predicate = cb.like(doctorRoot.get("specializzazion"), "%" + filtroDoctorRequest.getSpecializzazione() + "%");
            predicateList.add(predicate);
        }
        if (filtroDoctorRequest.getData_nascita()!= null ) {
            Predicate predicate = cb.equal(doctorRoot.get("data_nascita"),  filtroDoctorRequest.getData_nascita() );
            predicateList.add(predicate);
        }
        if (filtroDoctorRequest.getData_nascita()!= null ) {
            Predicate predicate = cb.lessThanOrEqualTo(doctorRoot.get("data_nascita"),  filtroDoctorRequest.getData_nascita() );
            predicateList.add(predicate);
        }
        if (filtroDoctorRequest.getData_nascita()!= null ) {
            Predicate predicate = cb.greaterThanOrEqualTo(doctorRoot.get("data_nascita"),  filtroDoctorRequest.getData_nascita() );
            predicateList.add(predicate);
        }



        cq.where(predicateList.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();

    }
}
