package fr.epsi.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produit {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    public Produit(String nom) {
        this.nom = nom;
    }

    public Produit() {}

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
