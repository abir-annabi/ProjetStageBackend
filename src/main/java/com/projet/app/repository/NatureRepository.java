package com.projet.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.app.models.Nature;

public interface NatureRepository extends JpaRepository<Nature, Long> {
	
}
