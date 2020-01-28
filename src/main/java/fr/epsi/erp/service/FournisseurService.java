package fr.epsi.erp.service;

import fr.epsi.erp.dto.FournisseurAddProduit;
import fr.epsi.erp.dto.FournisseurCreate;
import fr.epsi.erp.dto.FournisseurWithLink;
import fr.epsi.erp.model.Constant;
import fr.epsi.erp.model.Fournisseur;
import fr.epsi.erp.model.FournisseurProduit;
import fr.epsi.erp.model.Produit;
import fr.epsi.erp.repository.FournisseurProduitRepository;
import fr.epsi.erp.repository.FournisseurRepository;
import fr.epsi.erp.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private FournisseurProduitRepository fournisseurProduitRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public List<FournisseurWithLink> getAll() {

        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
        List<FournisseurWithLink> fournisseurWithLinks = new ArrayList<>();

        for (Fournisseur fournisseur : fournisseurs) {
            FournisseurWithLink fournisseurWithLink = new FournisseurWithLink(fournisseur.getId(), fournisseur.getNom(), Constant.baseUrl + "/api/" + fournisseur.getId() + "/produits");
            fournisseurWithLinks.add(fournisseurWithLink);
        }

        return fournisseurWithLinks;
    }

    public FournisseurWithLink post(FournisseurCreate fournisseurCreate) {

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setNom(fournisseurCreate.getNom());
        fournisseur = fournisseurRepository.save(fournisseur);

        FournisseurWithLink fournisseurWithLink = new FournisseurWithLink(fournisseur.getId(), fournisseur.getNom(), Constant.baseUrl + "/api/" + fournisseur.getId() + "/produits");

        return fournisseurWithLink;
    }

    public List<FournisseurProduit> addProduit(FournisseurAddProduit fournisseurAddProduit) throws Exception {

        Optional<Produit> optionalProduit = produitRepository.findById(fournisseurAddProduit.getIdProduit());

        if (!optionalProduit.isPresent()) {
            throw new Exception();
        }

        Produit produit = optionalProduit.get();
        FournisseurProduit fournisseurProduit = new FournisseurProduit(produit, fournisseurAddProduit.getCoutUnitaire());

        fournisseurProduit = fournisseurProduitRepository.save(fournisseurProduit);

        Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(fournisseurAddProduit.getIdFournisseur());

        if (!optionalFournisseur.isPresent()) {
            throw new Exception();
        }

        Fournisseur fournisseur = optionalFournisseur.get();

        List<FournisseurProduit> fournisseurProduits = fournisseur.getFournisseurProduits();

        fournisseurProduits.add(fournisseurProduit);

        fournisseur.setFournisseurProduits(fournisseurProduits);

        fournisseur = fournisseurRepository.save(fournisseur);

        return fournisseur.getFournisseurProduits();
    }
}
