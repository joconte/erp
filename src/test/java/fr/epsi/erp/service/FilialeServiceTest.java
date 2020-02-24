package fr.epsi.erp.service;

import fr.epsi.erp.dto.AchatCreate;
import fr.epsi.erp.dto.AchatWithLink;
import fr.epsi.erp.dto.FilialeWithLink;
import fr.epsi.erp.model.*;
import fr.epsi.erp.repository.AchatRepository;
import fr.epsi.erp.repository.FilialeRepository;
import fr.epsi.erp.repository.FournisseurProduitRepository;
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

public class FilialeServiceTest {

    @Mock
    private FilialeRepository filialeRepository;

    @Mock
    private FournisseurProduitRepository fournisseurProduitRepository;

    @Mock
    private AchatRepository achatRepository;

    @InjectMocks
    private FilialeService filialeService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll() {

        // Arrange
        List<Filiale> filiales = new ArrayList<>();
        Filiale filiale = new Filiale();
        filiale.setNom("Test");
        filiales.add(filiale);

        // Mock
        Mockito.when(filialeRepository.findAll()).thenReturn(filiales);

        // Act
        List<FilialeWithLink> filialeWithLinks = filialeService.getAll();

        // Assert
        assertThat(filialeWithLinks.size() == 1);
        assertThat(filialeWithLinks.get(0).getNom().equals(filiale.getNom()));
    }

    @Test
    public void addAchat() throws Exception {

        // Arrange
        Achat achat = new Achat();
        achat.setId(55555L);
        List<Achat> achats = new ArrayList<>();
        achats.add(achat);
        AchatCreate achatCreate = new AchatCreate(123456789L, 789456123L, 1000L);

        FournisseurProduit fourniProd = new FournisseurProduit();
        fourniProd.setId(789456123L);
        Optional<FournisseurProduit> optionalFournisseurProduit = Optional.of(fourniProd);

        Filiale filiale = new Filiale("Microsoft", achats);
        filiale.setId(123456789L);
        Optional<Filiale> optionalFiliale = Optional.of(filiale);

        // Mock
        Mockito.when(fournisseurProduitRepository.findById(Mockito.any(Long.class))).thenReturn(optionalFournisseurProduit);
        Mockito.when(achatRepository.save(Mockito.any(Achat.class))).thenReturn(achat);
        Mockito.when(filialeRepository.findById(Mockito.any(Long.class))).thenReturn(optionalFiliale);


        // Act
        List<AchatWithLink> listAchatWithLink = filialeService.addAchat(achatCreate);

        // Assert
        assertThat(listAchatWithLink.size() == 1);
        assertThat(listAchatWithLink.get(0).getId() == achat.getId());
        assertThat(listAchatWithLink.get(0).getFilialeLink().equals(Constant.baseUrl + "/api/filiale/" + filiale.getId()));
        assertThat(listAchatWithLink.get(0).getFournisseurProduitLink().equals(Constant.baseUrl + "/api/fournisseurs/" + fourniProd.getId() + "/produits"));

    }

    @Test
    public void getAchats() throws Exception {

        // Arrange
        FournisseurProduit fournisseurProduit = new FournisseurProduit();
        fournisseurProduit.setId(789456123L);

        Achat achat = new Achat();
        achat.setFournisseurProduit(fournisseurProduit);
        achat.setId(456456456L);
        List<Achat> achats = new ArrayList<>();
        achats.add(achat);


        Filiale filiale = new Filiale();
        filiale.setNom("Test");
        filiale.setId(123456789L);
        filiale.setAchats(achats);
        Optional<Filiale> optionalFiliale = Optional.of(filiale);

        // Mock
        Mockito.when(filialeRepository.findById(Mockito.any(Long.class))).thenReturn(optionalFiliale);

        // Act
        List<AchatWithLink> achatWithLinks = filialeService.getAchats(filiale.getId());

        // Assert
        assertThat(achatWithLinks.size() == 1);
        assertThat(achatWithLinks.get(0).getId().equals(achat.getId()));
        assertThat(achatWithLinks.get(0).getFilialeLink().equals(Constant.baseUrl + "/api/filiale/" + filiale.getId()));
        assertThat(achatWithLinks.get(0).getFournisseurProduitLink().equals(Constant.baseUrl + "/api/fournisseurs/" + fournisseurProduit.getId() + "/produits"));

    }
}