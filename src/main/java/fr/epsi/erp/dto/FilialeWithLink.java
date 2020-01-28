package fr.epsi.erp.dto;

public class FilialeWithLink {

    private Long id;

    private String nom;

    private String achatsLink;

    public FilialeWithLink(Long id, String nom, String achatsLink) {
        this.id = id;
        this.nom = nom;
        this.achatsLink = achatsLink;
    }

    public FilialeWithLink() {}

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

    public String getAchatsLink() {
        return achatsLink;
    }

    public void setAchatsLink(String achatsLink) {
        this.achatsLink = achatsLink;
    }
}
