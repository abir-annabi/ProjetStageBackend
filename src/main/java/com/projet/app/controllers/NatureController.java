	package com.projet.app.controllers;
	import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.app.models.Nature;
import com.projet.app.services.NatureService;
	
@RestController
@RequestMapping("/api/natures")
public class NatureController {
    private final NatureService service;

    public NatureController(NatureService service) {
        this.service = service;
    }

    // Endpoint pour ajouter une nature à un TypeDemande spécifique
    @PostMapping("/typedemandes/{typeDemandeId}/natures")
    public Nature addNatureToTypeDemande(@PathVariable Long typeDemandeId, @RequestBody Nature nature) {
        return service.addNatureToTypeDemande(typeDemandeId, nature);
    }

    // Autres endpoints existants...
    @GetMapping
    public List<Nature> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Nature getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public Nature create(@RequestBody Nature nature) { return service.save(nature); }

    @PutMapping("/{id}")
    public Nature update(@PathVariable Long id, @RequestBody Nature nature) {
        // Récupérer la nature existante
        Nature existingNature = service.getById(id);
        if (existingNature == null) {
            throw new IllegalArgumentException("Nature non trouvée avec l'ID : " + id);
        }

        // Conserver le typeDemande existant
        nature.setTypeDemande(existingNature.getTypeDemande());

        // Mettre à jour la nature
        nature.setId(id);
        return service.save(nature);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}