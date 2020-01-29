package fr.epsi.erp.controller;

import fr.epsi.erp.dto.ProduitCreate;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import fr.epsi.erp.model.Produit;
import fr.epsi.erp.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProduitController implements IProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public List<Produit> getAll() {
        return produitRepository.findAll();
    }

    @Override
    public Produit post(ProduitCreate produitCreate) throws ExceptionFonctionnnelle {

        if (StringUtils.isEmpty(produitCreate.getNom())) {
            throw new ExceptionFonctionnnelle("Le nom du produit est obligatoire.");
        }

        Optional<Produit> optionalProduit = produitRepository.findFirstByNom(produitCreate.getNom());

        if (optionalProduit.isPresent()) {
            throw new ExceptionFonctionnnelle("Le produit " + produitCreate.getNom() + " existe déjà.");
        }

        Produit produit = new Produit(produitCreate.getNom());
        return produitRepository.save(produit);
    }

    @Override
    public Produit getById(Long id) throws Exception {

        Optional<Produit> optionalProduit = produitRepository.findById(id);

        if (!optionalProduit.isPresent()) {
            throw new ExceptionFonctionnnelle("Aucun produit pour l'id " + id);
        }

        return optionalProduit.get();
    }
}
