package fr.epsi.erp.controller;

import fr.epsi.erp.dto.ProduitCreate;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import fr.epsi.erp.model.Produit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="API pour effectuer des actions sur les produits.")
@RequestMapping("/api/produits")
public interface IProduitController {

    @ApiOperation(value = "Permet de récupérer tous les produits.")
    @GetMapping
    List<Produit> getAll();

    @ApiOperation(value = "Permet de rajouter un produit.")
    @PostMapping
    Produit post(@RequestBody ProduitCreate produitCreate) throws ExceptionFonctionnnelle;

    @ApiOperation(value = "Permet de récupérer un produit par son Identifiant.")
    @GetMapping("/{id}")
    Produit getById(@PathVariable Long id) throws Exception;
}
