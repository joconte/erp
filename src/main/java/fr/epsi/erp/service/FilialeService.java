package fr.epsi.erp.service;

import fr.epsi.erp.dto.AchatCreate;
import fr.epsi.erp.dto.AchatWithLink;
import fr.epsi.erp.dto.FilialeWithLink;
import fr.epsi.erp.model.Achat;
import fr.epsi.erp.model.Constant;
import fr.epsi.erp.model.Filiale;
import fr.epsi.erp.model.FournisseurProduit;
import fr.epsi.erp.repository.AchatRepository;
import fr.epsi.erp.repository.FilialeRepository;
import fr.epsi.erp.repository.FournisseurProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilialeService {

    @Autowired
    private FilialeRepository filialeRepository;

    @Autowired
    private FournisseurProduitRepository fournisseurProduitRepository;

    @Autowired
    private AchatRepository achatRepository;

    public List<FilialeWithLink> getAll() {

        List<FilialeWithLink> filialeWithLinks = new ArrayList<>();

        List<Filiale> filiales = filialeRepository.findAll();

        for (Filiale filiale : filiales) {
            FilialeWithLink filialeWithLink = new FilialeWithLink(filiale.getId(), filiale.getNom(), Constant.baseUrl + "/api/filiale/" + filiale.getId() + "/achats");
            filialeWithLinks.add(filialeWithLink);
        }

        return filialeWithLinks;
    }

    public List<AchatWithLink> addAchat(AchatCreate achatCreate) throws Exception {

        List<AchatWithLink> achatWithLinks = new ArrayList<>();

        Optional<FournisseurProduit> optionalFournisseurProduit = fournisseurProduitRepository.findById(achatCreate.getIdFournisseurProduit());

        if (!optionalFournisseurProduit.isPresent()) {
            throw new Exception();
        }

        FournisseurProduit fournisseurProduit = optionalFournisseurProduit.get();

        Achat achat = new Achat(new Date(), fournisseurProduit, achatCreate.getQuantite());

        achatRepository.save(achat);

        Optional<Filiale> optionalFiliale = filialeRepository.findById(achatCreate.getIdFiliale());

        if (!optionalFiliale.isPresent()) {
            throw new Exception();
        }

        Filiale filiale = optionalFiliale.get();

        List<Achat> achats = filiale.getAchats();

        for (Achat achatDb : achats) {
            AchatWithLink achatWithLink = new AchatWithLink(achatDb.getId(), achatDb.getDate(), Constant.baseUrl + "/api/filiale/" + achatCreate.getIdFiliale(), Constant.baseUrl + "/api/fournisseurs/" + achatCreate.getIdFournisseurProduit() + "/produits", achatDb.getQuantite());
            achatWithLinks.add(achatWithLink);
        }

        return achatWithLinks;
    }

    public List<AchatWithLink> getAchats(Long idFiliale) throws Exception {

        List<AchatWithLink> achatWithLinks = new ArrayList<>();

        Optional<Filiale> optionalFiliale = filialeRepository.findById(idFiliale);

        if (!optionalFiliale.isPresent()) {
            throw new Exception();
        }

        Filiale filiale = optionalFiliale.get();

        List<Achat> achats = filiale.getAchats();

        for (Achat achatDb : achats) {
            AchatWithLink achatWithLink = new AchatWithLink(achatDb.getId(), achatDb.getDate(), Constant.baseUrl + "/api/filiale/" + idFiliale, Constant.baseUrl + "/api/fournisseurs/" + achatDb.getFournisseurProduit().getId() + "/produits", achatDb.getQuantite());
            achatWithLinks.add(achatWithLink);
        }

        return achatWithLinks;
    }
}
