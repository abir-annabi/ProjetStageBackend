package com.projet.app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.models.Signataire;
import com.projet.app.repository.SignataireRepository;

@Service
public class SignataireService {

    @Autowired
    private SignataireRepository signataireRepository;

    // Ajouter un signataire (CMNPS généré automatiquement)
    public Signataire addSignataire(Signataire signataire) {
        signataire.setCmpps(generateCmpps());
        return signataireRepository.save(signataire);
    }

    // Obtenir tous les signataires
    public List<Signataire> getAllSignataires() {
        return signataireRepository.findAll();
    }

    // Obtenir un signataire par ID
    public Optional<Signataire> getSignataireById(Long id) {
        return signataireRepository.findById(id);
    }

    // Obtenir un signataire par CMNPS
    public Optional<Signataire> getSignataireByCmpps(String cmpps) {
        return signataireRepository.findByCmpps(cmpps);
    }

    // Modifier un signataire
    public Signataire updateSignataire(Long id, Signataire signataireDetails) {
        return signataireRepository.findById(id).map(signataire -> {
            signataire.setNomFr(signataireDetails.getNomFr());
            signataire.setPrenomFr(signataireDetails.getPrenomFr());
            signataire.setNomAr(signataireDetails.getNomAr());
            signataire.setPrenomAr(signataireDetails.getPrenomAr());
            signataire.setTelephone(signataireDetails.getTelephone());
            signataire.setEmail(signataireDetails.getEmail());
            signataire.setActif(signataireDetails.isActif());
            return signataireRepository.save(signataire);
        }).orElseThrow(() -> new RuntimeException("Signataire non trouvé"));
    }

    // Supprimer un signataire
    public void deleteSignataire(Long id) {
        signataireRepository.deleteById(id);
    }

    // Génération automatique du CMNPS (exemple avec UUID)
    private String generateCmpps() {
        return "CODE-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
