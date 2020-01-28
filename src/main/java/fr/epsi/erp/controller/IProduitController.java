package fr.epsi.erp.controller;

import fr.epsi.erp.dto.ProduitCreate;
import fr.epsi.erp.model.Produit;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/produits")
public interface IProduitController {

    @GetMapping
    List<Produit> getAll();

    @PostMapping
    Produit post(@RequestBody ProduitCreate produitCreate);

    @GetMapping
    @RequestMapping("{id}")
    Produit getById(@PathVariable Long id) throws Exception;
}
