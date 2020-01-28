package fr.epsi.erp.controller;

import fr.epsi.erp.dto.AchatCreate;
import fr.epsi.erp.dto.AchatWithLink;
import fr.epsi.erp.dto.FilialeCreate;
import fr.epsi.erp.dto.FilialeWithLink;
import fr.epsi.erp.model.Constant;
import fr.epsi.erp.model.Filiale;
import fr.epsi.erp.repository.FilialeRepository;
import fr.epsi.erp.service.FilialeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public FilialeWithLink post(FilialeCreate filialeCreate) {
        Filiale filiale = new Filiale();
        filiale.setNom(filialeCreate.getNom());
        filiale = filialeRepository.save(filiale);

        return new FilialeWithLink(filiale.getId(), filiale.getNom(), Constant.baseUrl + "/api/filiale/" + filiale.getId() + "/achats");
    }

    @Override
    public FilialeWithLink getById(Long id) throws Exception {
        Optional<Filiale> optionalFiliale = filialeRepository.findById(id);

        if (!optionalFiliale.isPresent()) {
            throw new Exception();
        }

        Filiale filiale = optionalFiliale.get();

        FilialeWithLink filialeWithLink = new FilialeWithLink(filiale.getId(), filiale.getNom(), Constant.baseUrl + "/api/filiale/" + filiale.getId() + "/achats");

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
