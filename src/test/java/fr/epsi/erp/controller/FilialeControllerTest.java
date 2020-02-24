package fr.epsi.erp.controller;

import fr.epsi.erp.dto.AchatCreate;
import fr.epsi.erp.dto.AchatWithLink;
import fr.epsi.erp.dto.FilialeCreate;
import fr.epsi.erp.dto.FilialeWithLink;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import fr.epsi.erp.model.Filiale;
import fr.epsi.erp.repository.FilialeRepository;
import fr.epsi.erp.service.FilialeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FilialeControllerTest {

    @Mock
    private FilialeService filialeService;

    @Mock
    private FilialeRepository filialeRepository;

    @InjectMocks
    private FilialeController filialeController;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getAll() {

        // Arrange
        FilialeWithLink filialeWithLink = new FilialeWithLink();
        filialeWithLink.setId(123456789L);
        filialeWithLink.setNom("test");
        List<FilialeWithLink> filialesWithLinks = new ArrayList<>();

        filialesWithLinks.add(filialeWithLink);

        // Mock
        Mockito.when(filialeService.getAll()).thenReturn(filialesWithLinks);

        // Act
        List<FilialeWithLink> filialeWithLinksACT = filialeController.getAll();

        // Assert
        assertThat(filialeWithLinksACT.size() == 1);
        assertThat(filialeWithLinksACT.get(0).getId().equals(filialeWithLink.getId()));
        assertThat(filialeWithLinksACT.get(0).getNom().equals(filialeWithLink.getNom()));
    }

    @Test
    public void post() throws ExceptionFonctionnnelle {

        // Arrange
        Filiale filiale = new Filiale();
        filiale.setId(15645L);
        filiale.setNom("tedhj");
        FilialeCreate filialeCreate = new FilialeCreate();
        filialeCreate.setNom("test");

        // Mock
        Mockito.when(filialeRepository.findFirstByNom(Mockito.any(String.class))).thenReturn(Optional.empty());
        Mockito.when(filialeRepository.save(Mockito.any(Filiale.class))).thenReturn(filiale);


        // Act
        FilialeWithLink filialeWithLinks = filialeController.post(filialeCreate);

        // Assert
        assertThat(filialeWithLinks.getNom().equals(filiale.getNom()));
        assertThat(filialeWithLinks.getId().equals(filiale.getId()));
    }

    @Test
    public void getById() throws Exception {

        // Arrange
        Filiale filiale = new Filiale();
        filiale.setId(15645L);
        filiale.setNom("tedhj");

        Optional<Filiale> optionalFiliale = Optional.of(filiale);

        // Mock
        Mockito.when(filialeRepository.findById(Mockito.any(Long.class))).thenReturn(optionalFiliale);


        // Act
        FilialeWithLink filialeWithLinks = filialeController.getById(74125L);

        // Assert
        assertThat(filialeWithLinks.getNom().equals(filiale.getNom()));
        assertThat(filialeWithLinks.getId().equals(filiale.getId()));
    }

    @Test
    public void addAchat() throws Exception {

        // Arrange
        AchatWithLink achatWithLink = new AchatWithLink();
        achatWithLink.setId(7414521541L);
        List<AchatWithLink> achatWithLinkList = new ArrayList<>();
        achatWithLinkList.add(achatWithLink);

        // Mock
        Mockito.when(filialeService.addAchat(Mockito.any(AchatCreate.class))).thenReturn(achatWithLinkList);

        // Act
        List<AchatWithLink> achatWithLinkACT = filialeController.addAchat(new AchatCreate());

        // Assert
        assertThat(achatWithLinkACT.size() == 1);
        assertThat(achatWithLinkACT.get(0).getId().equals(achatWithLink.getId()));

    }

    @Test
    public void getAchats() throws Exception {

        // Arrange
        AchatWithLink achatWithLink = new AchatWithLink();
        achatWithLink.setId(7414521541L);
        List<AchatWithLink> achatWithLinkList = new ArrayList<>();
        achatWithLinkList.add(achatWithLink);

        // Mock
        Mockito.when(filialeService.getAchats(Mockito.any(Long.class))).thenReturn(achatWithLinkList);

        // Act
        List<AchatWithLink> achatWithLinkACT = filialeController.getAchats(55555555L);

        // Assert
        assertThat(achatWithLinkACT.size() == 1);
        assertThat(achatWithLinkACT.get(0).getId().equals(achatWithLink.getId()));
    }
}