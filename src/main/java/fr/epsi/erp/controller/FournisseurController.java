package fr.epsi.erp.controller;

import fr.epsi.erp.dto.FournisseurAddProduit;
import fr.epsi.erp.dto.FournisseurCreate;
import fr.epsi.erp.dto.FournisseurWithLink;
import fr.epsi.erp.model.Constant;
import fr.epsi.erp.model.Fournisseur;
import fr.epsi.erp.model.FournisseurProduit;
import fr.epsi.erp.repository.FournisseurRepository;
import fr.epsi.erp.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FournisseurController implements IFournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Override
    public List<FournisseurWithLink> getAll() {
        return fournisseurService.getAll();
    }

    @Override
    public FournisseurWithLink post(FournisseurCreate fournisseurCreate) {
        return fournisseurService.post(fournisseurCreate);
    }

    @Override
    public FournisseurWithLink getById(Long id) throws Exception {
        Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(id);

        if (!optionalFournisseur.isPresent()) {
            throw new Exception();
        }

        Fournisseur fournisseur = optionalFournisseur.get();

        return new FournisseurWithLink(fournisseur.getId(), fournisseur.getNom(), Constant.baseUrl + "/api/" + fournisseur.getId() + "/produits");
    }

    @Override
    public List<FournisseurProduit> addProduit(FournisseurAddProduit fournisseurAddProduit) throws Exception {
        return fournisseurService.addProduit(fournisseurAddProduit);
    }

    @Override
    public List<FournisseurProduit> getProduits(Long id) throws Exception {
        Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(id);

        if (!optionalFournisseur.isPresent()) {
            throw new Exception();
        }

        Fournisseur fournisseur = optionalFournisseur.get();

        return fournisseur.getFournisseurProduits();
    }
}
