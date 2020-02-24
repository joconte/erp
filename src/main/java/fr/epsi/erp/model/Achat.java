package fr.epsi.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Achat {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @OneToOne
    private FournisseurProduit fournisseurProduit;

    private Long quantite;

    public Achat(Date date, FournisseurProduit fournisseurProduit, Long quantite) {
        this.date = date;
        this.fournisseurProduit = fournisseurProduit;
        this.quantite = quantite;
    }

    public Achat() {}

    public Long getId() {
        return id;
    }

    public void setId(Long p_id) {
        this.id = p_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FournisseurProduit getFournisseurProduit() {
        return fournisseurProduit;
    }

    public void setFournisseurProduit(FournisseurProduit fournisseurProduit) {
        this.fournisseurProduit = fournisseurProduit;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }
}
