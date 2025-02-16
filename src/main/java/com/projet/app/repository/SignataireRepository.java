package com.projet.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.app.models.Signataire;
import java.util.Optional;

@Repository
public interface SignataireRepository extends JpaRepository<Signataire, Long> {
    Optional<Signataire> findByCmpps(String cmpps); // Recherche d'un signataire par CMNPS
}
