package fr.epsi.erp.dto;

public class AchatCreate {

    private Long idFiliale;

    private Long idFournisseurProduit;

    private Long quantite;

    public AchatCreate(Long idFiliale, Long idFournisseurProduit, Long quantite) {
        this.idFiliale = idFiliale;
        this.idFournisseurProduit = idFournisseurProduit;
        this.quantite = quantite;
    }

    public AchatCreate() {}

    public Long getIdFiliale() {
        return idFiliale;
    }

    public void setIdFiliale(Long idFiliale) {
        this.idFiliale = idFiliale;
    }

    public Long getIdFournisseurProduit() {
        return idFournisseurProduit;
    }

    public void setIdFournisseurProduit(Long idFournisseurProduit) {
        this.idFournisseurProduit = idFournisseurProduit;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }
}
