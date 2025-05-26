package com.example.Patient.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "idPatient")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatient;

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

    @Column(name = "indrizzo")
    private String indrizzo;

    @Column(name = "allergies")
    private List<String> allergies;
}
