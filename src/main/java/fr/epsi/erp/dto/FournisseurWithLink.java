package fr.epsi.erp.dto;

public class FournisseurWithLink {

    private Long id;

    private String nom;

    private String fournisseurProduitLink;

    public FournisseurWithLink(Long id, String nom, String fournisseurProduitLink) {
        this.id = id;
        this.nom = nom;
        this.fournisseurProduitLink = fournisseurProduitLink;
    }

    public FournisseurWithLink() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFournisseurProduitLink() {
        return fournisseurProduitLink;
    }

    public void setFournisseurProduitLink(String fournisseurProduitLink) {
        this.fournisseurProduitLink = fournisseurProduitLink;
    }
}
