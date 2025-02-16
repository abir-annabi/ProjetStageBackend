package com.projet.app.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.app.models.Nature;
import com.projet.app.models.TypeDemande;
import com.projet.app.repository.NatureRepository;
import com.projet.app.repository.TypeDemandeRepository;

@Service
public class NatureService {
    private final NatureRepository repository;
    private final TypeDemandeRepository typeDemandeRepository;

    public NatureService(NatureRepository repository, TypeDemandeRepository typeDemandeRepository) {
        this.repository = repository;
        this.typeDemandeRepository = typeDemandeRepository;
    }

    // Méthode pour ajouter une nature à un TypeDemande spécifique
    public Nature addNatureToTypeDemande(Long typeDemandeId, Nature nature) {
        // Vérifiez que le TypeDemande existe
        TypeDemande typeDemande = typeDemandeRepository.findById(typeDemandeId)
            .orElseThrow(() -> new IllegalArgumentException("TypeDemande non trouvé avec l'ID : " + typeDemandeId));

        // Associe la nature au TypeDemande
        nature.setTypeDemande(typeDemande);

        // Sauvegarde la nature
        return repository.save(nature);
    }

    // Autres méthodes existantes...
    public List<Nature> getAll() { 
        return repository.findAll(); 
    }

    public Nature getById(Long id) { 
        return repository.findById(id).orElse(null); 
    }

    public Nature save(Nature nature) {
        // Si la nature existe déjà, conservez son typeDemande
        if (nature.getId() != null) {
            Nature existingNature = repository.findById(nature.getId()).orElse(null);
            if (existingNature != null) {
                nature.setTypeDemande(existingNature.getTypeDemande());
            }
        }

        // Si un typeDemande est fourni, vérifiez qu'il existe
        if (nature.getTypeDemande() != null && nature.getTypeDemande().getId() != null) {
            TypeDemande typeDemande = typeDemandeRepository.findById(nature.getTypeDemande().getId()).orElse(null);
            if (typeDemande == null) {
                throw new IllegalArgumentException("Le TypeDemande associé n'existe pas");
            }
            nature.setTypeDemande(typeDemande);
        }

        return repository.save(nature);
    }

    public void delete(Long id) { 
        repository.deleteById(id); 
    }
}