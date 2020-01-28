package fr.epsi.erp.service;

import fr.epsi.erp.dto.FournisseurWithLink;
import fr.epsi.erp.model.Fournisseur;
import fr.epsi.erp.repository.FournisseurRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FournisseurServiceTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

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
    }

    @Test
    public void post() {
    }

    @Test
    public void addProduit() {
    }
}