package fr.epsi.erp.dto;

public class FournisseurAddProduit {

    private Long idProduit;

    private double coutUnitaire;

    private Long idFournisseur;

    public FournisseurAddProduit(Long idProduit, double coutUnitaire, Long idFournisseur) {
        this.idProduit = idProduit;
        this.coutUnitaire = coutUnitaire;
        this.idFournisseur = idFournisseur;
    }

    public FournisseurAddProduit() {}

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public Long getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Long idFournisseur) {
        this.idFournisseur = idFournisseur;
    }
}
