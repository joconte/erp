package fr.epsi.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class FournisseurProduit {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Produit produit;

    private double coutUnitaire;

    public FournisseurProduit(Produit produit, double coutUnitaire) {
        this.produit = produit;
        this.coutUnitaire = coutUnitaire;
    }

    public FournisseurProduit() {}

    public Long getId() {
        return id;
    }

    public void setId(Long p_id) {
        this.id = p_id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }
}
