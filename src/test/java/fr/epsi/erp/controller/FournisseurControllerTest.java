package fr.epsi.erp.controller;

import fr.epsi.erp.dto.FournisseurAddProduit;
import fr.epsi.erp.dto.FournisseurCreate;
import fr.epsi.erp.dto.FournisseurWithLink;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import fr.epsi.erp.model.Fournisseur;
import fr.epsi.erp.model.FournisseurProduit;
import fr.epsi.erp.repository.FournisseurRepository;
import fr.epsi.erp.service.FournisseurService;
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


public class FournisseurControllerTest {

    @Mock
    private FournisseurService fournisseurService;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @InjectMocks
    private FournisseurController fournisseurController;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getAll() {

        // Arrange
        FournisseurWithLink fournisseurWithLink = new FournisseurWithLink();
        fournisseurWithLink.setId(123456789L);
        fournisseurWithLink.setNom("test");
        List<FournisseurWithLink> fournisseursWithLinks = new ArrayList<>();

        fournisseursWithLinks.add(fournisseurWithLink);

        // Mock
        Mockito.when(fournisseurService.getAll()).thenReturn(fournisseursWithLinks);

        // Act
        List<FournisseurWithLink> fournisseursWithLinksACT = fournisseurController.getAll();

        // Assert
        assertThat(fournisseursWithLinksACT.size() == 1);
        assertThat(fournisseursWithLinksACT.get(0).getId().equals(fournisseurWithLink.getId()));
        assertThat(fournisseursWithLinksACT.get(0).getNom().equals(fournisseurWithLink.getNom()));
    }

    @Test
    public void post() throws ExceptionFonctionnnelle {

        // Arrange
        FournisseurWithLink fournisseurWithLink = new FournisseurWithLink();
        fournisseurWithLink.setNom("test");

        FournisseurCreate fourniCreate = new FournisseurCreate();
        fourniCreate.setNom("dnernnerne");

        // Mock
        Mockito.when(fournisseurRepository.findFirstByNom(Mockito.any(String.class))).thenReturn(Optional.empty());
        Mockito.when(fournisseurService.post(Mockito.any(FournisseurCreate.class))).thenReturn(fournisseurWithLink);

        // Act
        FournisseurWithLink fournisseurWithLinkACT = fournisseurController.post(fourniCreate);

        // Assert
        assertThat(fournisseurWithLinkACT.getNom().equals(fournisseurWithLink.getNom()));
    }

    @Test
    public void getById() throws Exception {

        // Arrange
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setNom("ryntbrevec");

        Optional<Fournisseur> optionalFournisseur = Optional.of(fournisseur);

        // Mock
        Mockito.when(fournisseurRepository.findById(Mockito.any(Long.class))).thenReturn(optionalFournisseur);

        // Act
        FournisseurWithLink fournisseurWithLinkACT = fournisseurController.getById(123456L);

        // Assert
        assertThat(fournisseurWithLinkACT.getNom().equals(fournisseur.getNom()));
    }

    @Test
    public void addProduit() throws Exception {

        // Arrange
        FournisseurProduit fourniProduit = new FournisseurProduit();
        fourniProduit.setId(123456456L);

        List<FournisseurProduit> fournisseursProduits = new ArrayList<>();
        fournisseursProduits.add(fourniProduit);

        // Mock
        Mockito.when(fournisseurService.addProduit(Mockito.any(FournisseurAddProduit.class))).thenReturn(fournisseursProduits);

        // Act
        List<FournisseurProduit> fournisseursProduitsACT = fournisseurController.addProduit(new FournisseurAddProduit());

        // Assert
        assertThat(fournisseursProduitsACT.size() == 1);
        assertThat(fournisseursProduitsACT.get(0).getId().equals(fourniProduit.getId()));
    }

    @Test
    public void getProduits() throws Exception {

        // Arrange
        FournisseurProduit fourniProduit = new FournisseurProduit();
        fourniProduit.setId(123456789L);

        List<FournisseurProduit> fournisseursProduits = new ArrayList<>();
        fournisseursProduits.add(fourniProduit);

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setNom("hhbvgfd");
        fournisseur.setFournisseurProduits(fournisseursProduits);

        Optional<Fournisseur> optionalFournisseur = Optional.of(fournisseur);
        // Mock
        Mockito.when(fournisseurRepository.findById(Mockito.any(Long.class))).thenReturn(optionalFournisseur);

        // Act
        List<FournisseurProduit> fournisseursProduitsACT = fournisseurController.getProduits(7452445L);

        // Assert
        assertThat(fournisseursProduitsACT.size() == 1);
        assertThat(fournisseursProduitsACT.get(0).getId().equals(fourniProduit.getId()));
    }
}