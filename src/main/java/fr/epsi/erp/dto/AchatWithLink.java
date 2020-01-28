package fr.epsi.erp.dto;

import java.util.Date;

public class AchatWithLink {

    private Long id;

    private Date date;

    private String filialeLink;

    private String fournisseurProduitLink;

    private Long quantite;

    public AchatWithLink(Long id, Date date, String filialeLink, String fournisseurProduitLink, Long quantite) {
        this.id = id;
        this.date = date;
        this.filialeLink = filialeLink;
        this.fournisseurProduitLink = fournisseurProduitLink;
        this.quantite = quantite;
    }

    public AchatWithLink() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFilialeLink() {
        return filialeLink;
    }

    public void setFilialeLink(String filialeLink) {
        this.filialeLink = filialeLink;
    }

    public String getFournisseurProduitLink() {
        return fournisseurProduitLink;
    }

    public void setFournisseurProduitLink(String fournisseurProduitLink) {
        this.fournisseurProduitLink = fournisseurProduitLink;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }
}
