package com.projet.app.controllers;

import java.util.List;
import java.util.Optional;

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

import com.projet.app.models.Signataire;
import com.projet.app.services.SignataireService;

@RestController
@RequestMapping("/api/signataires")
 // Autoriser les requêtes depuis n'importe quelle origine
public class SignataireController {

    @Autowired
    private SignataireService signataireService;

    // Ajouter un signataire
    @PostMapping
    public ResponseEntity<Signataire> createSignataire(@RequestBody Signataire signataire) {
        return ResponseEntity.ok(signataireService.addSignataire(signataire));
    }

    // Récupérer tous les signataires
    @GetMapping
    public ResponseEntity<List<Signataire>> getAllSignataires() {
        return ResponseEntity.ok(signataireService.getAllSignataires());
    }

    // Récupérer un signataire par ID
    @GetMapping("/{id}")
    public ResponseEntity<Signataire> getSignataireById(@PathVariable("id") Long id) {
        Optional<Signataire> signataire = signataireService.getSignataireById(id);
        return signataire.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Récupérer un signataire par CMNPS
    @GetMapping("/cmpps/{cmpps}")
    public ResponseEntity<Signataire> getSignataireByCmpps(@PathVariable("cmpps") String cmpps) {
        Optional<Signataire> signataire = signataireService.getSignataireByCmpps(cmpps);
        return signataire.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Modifier un signataire
    @PutMapping("/{id}")
    public ResponseEntity<Signataire> updateSignataire(@PathVariable("id") Long id, @RequestBody Signataire signataireDetails) {
        return ResponseEntity.ok(signataireService.updateSignataire(id, signataireDetails));
    }

    // Supprimer un signataire
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSignataire(@PathVariable("id") Long id) {
        signataireService.deleteSignataire(id);
        return ResponseEntity.noContent().build();
    }
}
