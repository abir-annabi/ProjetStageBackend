package com.projet.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.models.Type;
import com.projet.app.repository.TypeRepository;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public Type createType(Type type) {
        // Si le code est null ou vide, générer un nouveau code
        if (type.getCode() == null || type.getCode().isEmpty()) {
            String generatedCode = "TYPE" + System.currentTimeMillis();
            type.setCode(generatedCode);  // Génération du code ici
        }
        return typeRepository.save(type);
    }


    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    public Optional<Type> getTypeById(Long id) {
        return typeRepository.findById(id);
    }

    public Type updateType(Long id, Type typeDetails) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found with ID: " + id));

        type.setCode(typeDetails.getCode());
        type.setLibelleAr(typeDetails.getLibelleAr());
        type.setLibelleFr(typeDetails.getLibelleFr());


        return typeRepository.save(type);
    }

    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}