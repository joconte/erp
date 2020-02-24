package fr.epsi.erp.controller;

import fr.epsi.erp.dto.AchatCreate;
import fr.epsi.erp.dto.AchatWithLink;
import fr.epsi.erp.dto.FilialeCreate;
import fr.epsi.erp.dto.FilialeWithLink;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="API pour effectuer des actions sur les filiales.")
@RequestMapping("/api/filiale")
public interface IFilialeController {

    @ApiOperation(value = "Permet de récupérer toutes les filiales.")
    @GetMapping
    List<FilialeWithLink> getAll();

    @ApiOperation(value = "Permet de rajouter une filiale.")
    @PostMapping
    FilialeWithLink post(@RequestBody FilialeCreate filialeCreate) throws ExceptionFonctionnnelle;

    @ApiOperation(value = "Permet de récupérer une filiale par son Identifiant.")
    @GetMapping("/{id}")
    FilialeWithLink getById(@PathVariable Long id) throws Exception;

    @ApiOperation(value = "Permet d'ajouter un achat à une filiale.")
    @PostMapping("/achats")
    List<AchatWithLink> addAchat(@RequestBody AchatCreate achatCreate) throws Exception;

    @ApiOperation(value = "Permet de récupérer tous les achats que la filiale possède (par son Identifiant).")
    @GetMapping("/{id}/achats")
    List<AchatWithLink> getAchats(@PathVariable Long id) throws Exception;

    @ApiOperation(value = "Permet de supprimer une filiale par son id.")
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
