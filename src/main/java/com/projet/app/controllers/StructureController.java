package com.projet.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.app.models.Structure;
import com.projet.app.services.StructureService;

@RestController
@RequestMapping("/api/structures")
public class StructureController {

    @Autowired
    private StructureService structureService;

    // Créer une structure
    @PostMapping
    public Structure createStructure(@RequestBody Structure structure) {
        return structureService.createStructure(structure);
    }

    // Récupérer toutes les structures
    @GetMapping
    public List<Structure> getAllStructures() {
        return structureService.getAllStructures();
    }

    // Récupérer une structure par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Structure> getStructureById(@PathVariable Long id) {
        return structureService.getStructureById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mettre à jour une structure
    @PutMapping("/{id}")
    public ResponseEntity<Structure> updateStructure(@PathVariable Long id, @RequestBody Structure structureDetails) {
        Structure updatedStructure = structureService.updateStructure(id, structureDetails);
        return ResponseEntity.ok(updatedStructure);
    }

    // Supprimer une structure
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStructure(@PathVariable Long id) {
        structureService.deleteStructure(id);
        return ResponseEntity.noContent().build();
    }
}