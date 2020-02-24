package fr.epsi.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Filiale {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    @OneToMany
    private List<Achat> achats;

    public Filiale(String nom, List<Achat> achats) {
        this.nom = nom;
        this.achats = achats;
    }

    public Filiale() {}

    public Long getId() {
        return id;
    }

    public void setId(Long p_id) {
        this.id = p_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Achat> getAchats() {
        return achats;
    }

    public void setAchats(List<Achat> achats) {
        this.achats = achats;
    }
}
