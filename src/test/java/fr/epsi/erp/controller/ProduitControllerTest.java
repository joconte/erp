package fr.epsi.erp.controller;

import fr.epsi.erp.dto.ProduitCreate;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import fr.epsi.erp.model.Produit;
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

public class ProduitControllerTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitController produitController;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll() {

        // Arrange
        List<Produit> produits = new ArrayList<>();
        Produit produit = new Produit();
        produit.setNom("telephone");
        produits.add(produit);

        // Mock
        Mockito.when(produitRepository.findAll()).thenReturn(produits);

        // Act
        List<Produit> produits2 = produitController.getAll();

        // Assert
        assertThat(produits2.size() == 1);
        assertThat(produits2.get(0).getNom().equals(produit.getNom()));
    }

    @Test
    public void post() throws ExceptionFonctionnnelle {

        // Arrange
        Produit produit = new Produit("test");

        ProduitCreate produitCreate = new ProduitCreate();
        produitCreate.setNom("prodCreateProduitTest");

        // Mock
        Mockito.when(produitRepository.findFirstByNom(Mockito.any(String.class))).thenReturn(Optional.empty());
        Mockito.when(produitRepository.save(Mockito.any(Produit.class))).thenReturn(produit);

        // Act
        Produit prod = produitController.post(produitCreate);

        // Assert
        assertThat(prod.getNom().equals(produit.getNom()));
    }

    @Test
    public void getById() throws Exception {

        // Arrange
        Produit produit = new Produit("test");
        produit.setId(123L);
        Optional<Produit> optionalProduit = Optional.of(produit);

        // Mock
        Mockito.when(produitRepository.findById(Mockito.any(Long.class))).thenReturn(optionalProduit);

        // Act
        Produit prod = produitController.getById(123456789L);

        // Assert
        assertThat(prod.getNom().equals(produit.getNom()));
        assertThat(prod.getId().equals(produit.getId()));
    }
}