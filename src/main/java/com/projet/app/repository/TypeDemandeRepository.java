package com.projet.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.app.models.TypeDemande;

public interface TypeDemandeRepository extends JpaRepository<TypeDemande, Long> {}
