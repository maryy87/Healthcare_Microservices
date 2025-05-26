package com.example.Doctor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor")
public class Doctor {


    @Id
    @Column(name = "idDoctor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoctor;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "email")
    private String email;

    @Column(name = "data_nascita")
    private LocalDate data_nascita;

    @Column(name = "codicefiscale")
    private String codicefiscale;

    @Column(name = "genere")
    private String genere;

    @Column(name = "numero_telefono")
    private String numero_telefono;

    @Column(name = "licenseNumber")
    private String licenseNumber;

    @Column(name = "specializzazione")
    private String specializzazione;


}
