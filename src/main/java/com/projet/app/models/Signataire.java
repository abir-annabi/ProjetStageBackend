	package com.projet.app.models;
	import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
	
	@Entity
	@Table(name = "signataire")
	public class Signataire {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	    private String nomFr;
	    private String prenomFr;
	    private String nomAr;
	    private String prenomAr;
	    private String telephone;
	    @Column(unique = true, nullable = false, updatable = false) // CMNPS unique et non modifiable
	    private String cmpps;    
	    private String email;
	    private boolean actif;
	
	    @ManyToOne
	    @JoinColumn(name = "structure_id", nullable = false)
	    private Structure structure;

	    // Ajoutez un getter pour structure_id
	    public Long getStructureId() {
	        return structure != null ? structure.getId() : null;
	    }
	
	
	    // Getters et Setters
	    public Long getId() {
	        return id;
	    }
	
	    public void setId(Long id) {
	        this.id = id;
	    }
	
	    public String getNomFr() {
	        return nomFr;
	    }
	
	    public void setNomFr(String nomFr) {
	        this.nomFr = nomFr;
	    }
	
	    public String getPrenomFr() {
	        return prenomFr;
	    }
	
	    public void setPrenomFr(String prenomFr) {
	        this.prenomFr = prenomFr;
	    }
	
	    public String getNomAr() {
	        return nomAr;
	    }
	
	    public void setNomAr(String nomAr) {
	        this.nomAr = nomAr;
	    }
	
	    public String getPrenomAr() {
	        return prenomAr;
	    }
	
	    public void setPrenomAr(String prenomAr) {
	        this.prenomAr = prenomAr;
	    }
	
	    public String getTelephone() {
	        return telephone;
	    }
	
	    public void setTelephone(String telephone) {
	        this.telephone = telephone;
	    }
	
	    public String getCmpps() {
	        return cmpps;
	    }
	
	    public void setCmpps(String cmpps) {
	        this.cmpps = cmpps;
	    }
	
	    public String getEmail() {
	        return email;
	    }
	
	    public void setEmail(String email) {
	        this.email = email;
	    }
	
	    public boolean isActif() {
	        return actif;
	    }
	
	    public void setActif(boolean actif) {
	        this.actif = actif;
	    }
	
	    public Structure getStructure() {
	        return structure;
	    }
	
	    public void setStructure(Structure structure) {
	        this.structure = structure;
	    }
	}
