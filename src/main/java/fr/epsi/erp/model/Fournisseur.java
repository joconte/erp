package fr.epsi.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Fournisseur {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    @OneToMany
    private List<FournisseurProduit> fournisseurProduits;

    public Fournisseur(String nom, List<FournisseurProduit> fournisseurProduits) {
        this.nom = nom;
        this.fournisseurProduits = fournisseurProduits;
    }

    public Fournisseur() {}

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<FournisseurProduit> getFournisseurProduits() {
        return fournisseurProduits;
    }

    public void setFournisseurProduits(List<FournisseurProduit> fournisseurProduits) {
        this.fournisseurProduits = fournisseurProduits;
    }
}
