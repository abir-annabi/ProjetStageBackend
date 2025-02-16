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

import com.projet.app.models.TypeDemande;
import com.projet.app.services.TypeDemandeService;

@RestController
@RequestMapping("/api/typedemandes")
public class TypeDemandeController {
    private final TypeDemandeService service;

    public TypeDemandeController(TypeDemandeService service) {
        this.service = service;
    }

    @GetMapping
    public List<TypeDemande> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public TypeDemande getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public TypeDemande create(@RequestBody TypeDemande typeDemande) { return service.save(typeDemande); }

    @PutMapping("/{id}")
    public TypeDemande update(@PathVariable Long id, @RequestBody TypeDemande typeDemande) {
        typeDemande.setId(id);
        return service.save(typeDemande);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
