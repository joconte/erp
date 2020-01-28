package fr.epsi.erp.dto;

public class FilialeCreate {

    private String nom;

    public FilialeCreate(String nom) {
        this.nom = nom;
    }

    public FilialeCreate() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
