package fr.epsi.erp.controller;

import fr.epsi.erp.dto.AchatCreate;
import fr.epsi.erp.dto.AchatWithLink;
import fr.epsi.erp.dto.FilialeCreate;
import fr.epsi.erp.dto.FilialeWithLink;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/filiale")
public interface IFilialeController {

    @GetMapping
    List<FilialeWithLink> getAll();

    @PostMapping
    FilialeWithLink post(@RequestBody FilialeCreate filialeCreate) throws ExceptionFonctionnnelle;

    @GetMapping
    @RequestMapping("/{id}")
    FilialeWithLink getById(@PathVariable Long id) throws Exception;

    @PostMapping
    @RequestMapping("/achats")
    List<AchatWithLink> addAchat(@RequestBody AchatCreate achatCreate) throws Exception;

    @GetMapping
    @RequestMapping("/{id}/achats")
    List<AchatWithLink> getAchats(@PathVariable Long id) throws Exception;
}
