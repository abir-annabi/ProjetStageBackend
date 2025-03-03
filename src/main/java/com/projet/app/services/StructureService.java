package com.projet.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.dto.StructureDTO;
import com.projet.app.models.Structure;
import com.projet.app.models.Type;
import com.projet.app.repository.StructureRepository;
import com.projet.app.repository.TypeRepository;



@Service
public class StructureService {

    @Autowired
    private StructureRepository structureRepository;
    @Autowired
    private TypeRepository typeRepository; 

  

    // Récupérer toutes les structures
    public List<StructureDTO> getAllStructures() {
    	 List<Structure> structures = structureRepository.findAll();
    	    return structures.stream().map(StructureDTO::new).collect(Collectors.toList());
    }

    // Récupérer une structure par son ID
    public Optional<Structure> getStructureById(Long id) {
        return structureRepository.findById(id);
    }
    
    public Structure createStructure(Structure structure) {
        if (structure.getParentStructure() != null ) {
            Structure parent = structureRepository.findById(structure.getParentStructure().getId())
                    .orElseThrow(() -> new RuntimeException("Parent non trouvé"));
            structure.setParentStructure(parent);
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
            structureRepository.save(parent);
        }
        if (structureDetails.getType() != null) {
            Type type = typeRepository.findById(structureDetails.getType().getId())
                    .orElseThrow(() -> new RuntimeException("Type non trouvé"));
            structure.setType(type);
        }
        return structureRepository.save(structure);
    }


    // Supprimer une structure
    public void deleteStructure(Long id) {
        structureRepository.deleteById(id);
    }
}