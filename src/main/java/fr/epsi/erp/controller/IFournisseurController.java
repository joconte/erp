package fr.epsi.erp.controller;

import fr.epsi.erp.dto.FournisseurAddProduit;
import fr.epsi.erp.dto.FournisseurCreate;
import fr.epsi.erp.dto.FournisseurWithLink;
import fr.epsi.erp.model.FournisseurProduit;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/fournisseurs")
public interface IFournisseurController {

    @GetMapping
    List<FournisseurWithLink> getAll();

    @PostMapping
    FournisseurWithLink post(@RequestBody FournisseurCreate fournisseurCreate);

    @GetMapping
    @RequestMapping("{id}")
    FournisseurWithLink getById(@PathVariable Long id) throws Exception;

    @PostMapping
    @RequestMapping("/produits")
    List<FournisseurProduit> addProduit(@RequestBody FournisseurAddProduit fournisseurAddProduit) throws Exception;

    @GetMapping
    @RequestMapping("/{id}/produits")
    List<FournisseurProduit> getProduits(@PathVariable Long id) throws Exception;
}
