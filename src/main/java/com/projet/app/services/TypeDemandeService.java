package com.projet.app.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.app.models.Nature;
import com.projet.app.models.TypeDemande;
import com.projet.app.repository.NatureRepository;
import com.projet.app.repository.TypeDemandeRepository;

@Service
public class TypeDemandeService {
    private final TypeDemandeRepository typeDemandeRepository;
    private final NatureRepository natureRepository;

    public TypeDemandeService(TypeDemandeRepository typeDemandeRepository, NatureRepository natureRepository) {
        this.typeDemandeRepository = typeDemandeRepository;
        this.natureRepository = natureRepository;
    }

    public List<TypeDemande> getAll() {
        return typeDemandeRepository.findAll();
    }

    public TypeDemande getById(Long id) {
        return typeDemandeRepository.findById(id).orElse(null);
    }

    public TypeDemande save(TypeDemande typeDemande) {
        if (typeDemande.getNatures() != null) {
            for (Nature nature : typeDemande.getNatures()) {
                nature.setTypeDemande(typeDemande); // Associe le TypeDemande à chaque Nature
            }
        }
        return typeDemandeRepository.save(typeDemande);
    }

    public void delete(Long id) {
        typeDemandeRepository.deleteById(id);
    }
}
