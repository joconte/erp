package fr.epsi.erp.controller;

import fr.epsi.erp.dto.FournisseurAddProduit;
import fr.epsi.erp.dto.FournisseurCreate;
import fr.epsi.erp.dto.FournisseurWithLink;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import fr.epsi.erp.model.FournisseurProduit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="API pour effectuer des actions sur les fournisseurs.")
@RequestMapping("/api/fournisseurs")
public interface IFournisseurController {

    @ApiOperation(value = "Permet de récupérer tous les fournisseurs.")
    @GetMapping
    List<FournisseurWithLink> getAll();

    @ApiOperation(value = "Permet de rajouter un fournisseur.")
    @PostMapping
    FournisseurWithLink post(@RequestBody FournisseurCreate fournisseurCreate) throws ExceptionFonctionnnelle;

    @ApiOperation(value = "Permet de récupérer un fournisseur par son Identifiant.")
    @GetMapping("{id}")
    FournisseurWithLink getById(@PathVariable Long id) throws Exception;

    @ApiOperation(value = "Permet d'ajouter un produit à un fournisseur.")
    @PostMapping("/produits")
    List<FournisseurProduit> addProduit(@RequestBody FournisseurAddProduit fournisseurAddProduit) throws Exception;

    @ApiOperation(value = "Permet de récupérer tous les produits que le fournisseur possède (par son Identifiant).")
    @GetMapping("/{id}/produits")
    List<FournisseurProduit> getProduits(@PathVariable Long id) throws Exception;
}
