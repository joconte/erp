package fr.epsi.erp.controller;

import fr.epsi.erp.dto.FournisseurAddProduit;
import fr.epsi.erp.dto.FournisseurCreate;
import fr.epsi.erp.dto.FournisseurWithLink;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import fr.epsi.erp.model.Fournisseur;
import fr.epsi.erp.model.FournisseurProduit;
import fr.epsi.erp.repository.FournisseurRepository;
import fr.epsi.erp.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    public FournisseurWithLink post(FournisseurCreate fournisseurCreate) throws ExceptionFonctionnnelle {

        if (StringUtils.isEmpty(fournisseurCreate.getNom())) {
            throw new ExceptionFonctionnnelle("Le nom du fournisseur est obligatoire.");
        }

        Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findFirstByNom(fournisseurCreate.getNom());

        if (optionalFournisseur.isPresent()) {
            throw new ExceptionFonctionnnelle("Le fournisseur " + fournisseurCreate.getNom() + " existe déjà.");
        }


        return fournisseurService.post(fournisseurCreate);
    }

    @Override
    public FournisseurWithLink getById(Long id) throws Exception {
        Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(id);

        if (!optionalFournisseur.isPresent()) {
            throw new ExceptionFonctionnnelle("Aucun fournisseur pour l'id " + id);
        }

        Fournisseur fournisseur = optionalFournisseur.get();

        return new FournisseurWithLink(fournisseur.getId(), fournisseur.getNom(), "/api/fournisseurs/" + fournisseur.getId() + "/produits");
    }

    @Override
    public List<FournisseurProduit> addProduit(FournisseurAddProduit fournisseurAddProduit) throws Exception {
        return fournisseurService.addProduit(fournisseurAddProduit);
    }

    @Override
    public List<FournisseurProduit> getProduits(Long id) throws Exception {
        Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(id);

        if (!optionalFournisseur.isPresent()) {
            throw new ExceptionFonctionnnelle("Aucun fournisseur pour l'id " + id);
        }

        Fournisseur fournisseur = optionalFournisseur.get();

        return fournisseur.getFournisseurProduits();
    }
}
