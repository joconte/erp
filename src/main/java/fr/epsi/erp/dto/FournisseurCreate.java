package fr.epsi.erp.dto;

public class FournisseurCreate {

    private String nom;

    public FournisseurCreate(String nom) {
        this.nom = nom;
    }

    public FournisseurCreate() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
