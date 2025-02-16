package com.projet.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.models.Structure;
import com.projet.app.repository.StructureRepository;

@Service
public class StructureService {

    @Autowired
    private StructureRepository structureRepository;

  

    // Récupérer toutes les structures
    public List<Structure> getAllStructures() {
        return structureRepository.findAll();
    }

    // Récupérer une structure par son ID
    public Optional<Structure> getStructureById(Long id) {
        return structureRepository.findById(id);
    }
    
    public Structure createStructure(Structure structure) {
        if (structure.getParentStructure() != null) {
            Structure parent = structureRepository.findById(structure.getParentStructure().getId())
                    .orElseThrow(() -> new RuntimeException("Parent non trouvé"));

            parent.getChildStructures().add(structure);
            structureRepository.save(parent); // Sauvegarde le parent mis à jour
        }

        return structureRepository.save(structure);
    }


    public Structure updateStructure(Long id, Structure structureDetails) {
        Structure structure = structureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Structure non trouvée avec l'ID : " + id));

        structure.setLibelleAr(structureDetails.getLibelleAr());
        structure.setLibelleFr(structureDetails.getLibelleFr());
        structure.setAdresse(structureDetails.getAdresse());

        if (structureDetails.getParentStructure() != null) {
            Structure parent = structureRepository.findById(structureDetails.getParentStructure().getId())
                    .orElseThrow(() -> new RuntimeException("Parent non trouvé"));

            structure.setParentStructure(parent);
            parent.getChildStructures().add(structure);
            structureRepository.save(parent);
        }

        return structureRepository.save(structure);
    }


    // Supprimer une structure
    public void deleteStructure(Long id) {
        structureRepository.deleteById(id);
    }
}