package fr.epsi.erp.controller;

import fr.epsi.erp.dto.AchatCreate;
import fr.epsi.erp.dto.AchatWithLink;
import fr.epsi.erp.dto.FilialeCreate;
import fr.epsi.erp.dto.FilialeWithLink;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import fr.epsi.erp.model.Constant;
import fr.epsi.erp.model.Filiale;
import fr.epsi.erp.repository.FilialeRepository;
import fr.epsi.erp.service.FilialeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FilialeController implements IFilialeController {

    @Autowired
    private FilialeService filialeService;

    @Autowired
    private FilialeRepository filialeRepository;

    @Override
    public List<FilialeWithLink> getAll() {
        return filialeService.getAll();
    }

    @Override
    public FilialeWithLink post(FilialeCreate filialeCreate) throws ExceptionFonctionnnelle {

        if (StringUtils.isEmpty(filialeCreate.getNom())) {
            throw new ExceptionFonctionnnelle("Le nom de la filiale est obligatoire.");
        }

        Optional<Filiale> optionalFiliale = filialeRepository.findFirstByNom(filialeCreate.getNom());

        if(optionalFiliale.isPresent()) {
            throw new ExceptionFonctionnnelle("La filiale " + filialeCreate.getNom() + " existe déjà.");
        }

        Filiale filiale = new Filiale();
        filiale.setNom(filialeCreate.getNom());
        filiale = filialeRepository.save(filiale);

        return new FilialeWithLink(filiale.getId(), filiale.getNom(), "/api/filiale/" + filiale.getId() + "/achats");
    }

    @Override
    public FilialeWithLink getById(Long id) throws Exception {
        Optional<Filiale> optionalFiliale = filialeRepository.findById(id);

        if (!optionalFiliale.isPresent()) {
            throw new ExceptionFonctionnnelle("Aucune filiale pour l'id " + id);
        }

        Filiale filiale = optionalFiliale.get();

        FilialeWithLink filialeWithLink = new FilialeWithLink(filiale.getId(), filiale.getNom(), "/api/filiale/" + filiale.getId() + "/achats");

        return filialeWithLink;
    }

    @Override
    public List<AchatWithLink> addAchat(AchatCreate achatCreate) throws Exception {
        return filialeService.addAchat(achatCreate);
    }

    @Override
    public List<AchatWithLink> getAchats(Long id) throws Exception {
        return filialeService.getAchats(id);
    }
}
