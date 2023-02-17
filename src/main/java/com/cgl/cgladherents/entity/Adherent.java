package com.cgl.cgladherents.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "adherents")
@Getter
@Setter
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email ;

    @Column(name = "date_paiement")
    private LocalDate date_paiement;

    @Column(name = "date_renouvellement")
    private LocalDate date_renouvellement;

    @Column(name = "email_envoye")
    private Boolean email_envoye;

    @Column(name = "adresse")
    private String adresse;

    @Column(name="telephone")
    private String telephone;
    public Boolean isEmail_envoye() {
        return email_envoye;
    }


}
