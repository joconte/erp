package fr.epsi.erp.controller;

import fr.epsi.erp.dto.ProduitCreate;
import fr.epsi.erp.model.Produit;
import fr.epsi.erp.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Produit post(ProduitCreate produitCreate) {
        Produit produit = new Produit(produitCreate.getNom());
        return produitRepository.save(produit);
    }

    @Override
    public Produit getById(Long id) throws Exception {

        Optional<Produit> optionalProduit = produitRepository.findById(id);

        if (!optionalProduit.isPresent()) {
            throw new Exception();
        }

        return optionalProduit.get();
    }
}
