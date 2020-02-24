package fr.epsi.erp.service;

import fr.epsi.erp.dto.FournisseurAddProduit;
import fr.epsi.erp.dto.FournisseurCreate;
import fr.epsi.erp.dto.FournisseurWithLink;
import fr.epsi.erp.model.Fournisseur;
import fr.epsi.erp.model.FournisseurProduit;
import fr.epsi.erp.model.Produit;
import fr.epsi.erp.repository.FournisseurProduitRepository;
import fr.epsi.erp.repository.FournisseurRepository;
import fr.epsi.erp.repository.ProduitRepository;
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

public class FournisseurServiceTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private FournisseurProduitRepository fournisseurProduitRepository;


    @InjectMocks
    private FournisseurService fournisseurService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll() {

        // Arrange
        List<Fournisseur> fournisseurs = new ArrayList<>();
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setNom("Test");
        fournisseurs.add(fournisseur);

        // Mock
        Mockito.when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        // Act
        List<FournisseurWithLink> fournisseurWithLinks = fournisseurService.getAll();

        // Assert
        assertThat(fournisseurWithLinks.size() == 1);
        assertThat(fournisseurWithLinks.get(0).getNom().equals(fournisseur.getNom()));
    }

    @Test
    public void post() {

        // Arrange
        FournisseurCreate fournisseurCreate = new FournisseurCreate("Test");
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setNom("Test");

        // Mock
        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseur);

        // Act
        FournisseurWithLink fournisseurWithLink = fournisseurService.post(fournisseurCreate);

        // Assert
        assertThat(fournisseurWithLink.getNom().equals("Test"));
    }

    @Test
    public void addProduit() throws Exception {

        // Arrange
        FournisseurAddProduit fournisseurAddProduitTest = new FournisseurAddProduit();

        Produit prod = new Produit("Telephone");
        Optional<Produit> optionalProduit = Optional.of(prod);
        FournisseurProduit fournisseurProd = new FournisseurProduit(prod, 17.0);

        Fournisseur fourni = new Fournisseur();
        fourni.setNom("LU");
        fourni.setFournisseurProduits(new ArrayList<>());
        Optional<Fournisseur> optionalFournisseur = Optional.of(fourni);


        // Mock
        Mockito.when(produitRepository.findById(Mockito.any(Long.class))).thenReturn(optionalProduit);
        Mockito.when(fournisseurProduitRepository.save(Mockito.any(FournisseurProduit.class))).thenReturn(fournisseurProd);
        Mockito.when(fournisseurRepository.findById(Mockito.any(Long.class))).thenReturn(optionalFournisseur);
        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fourni);

        // Act
        List<FournisseurProduit> listFourniProduit = fournisseurService.addProduit(fournisseurAddProduitTest);

        // Assert
        assertThat(listFourniProduit.size() == 1);
        assertThat(listFourniProduit.get(0).getCoutUnitaire() == 17.0);
    }
}