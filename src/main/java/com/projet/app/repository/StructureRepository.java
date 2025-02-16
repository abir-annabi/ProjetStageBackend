package com.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.app.models.Structure;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}