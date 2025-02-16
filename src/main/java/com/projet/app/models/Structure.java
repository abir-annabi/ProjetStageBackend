package com.projet.app.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "structure")
public class Structure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false)
    private String code;

    private String libelleAr;
    private String libelleFr;
    private String adresse;

    @OneToMany(mappedBy = "structure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("structure-users") 
    private Set<DBUser> users;

    @ManyToOne
    @JoinColumn(name = "parentStructure")
    @JsonIgnoreProperties("childStructures") // Remplace @JsonBackReference// ✔ Correction
    private Structure parentStructure;

    @OneToMany(mappedBy = "parentStructure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("structure-parent") // ✔ Correction : Correspondance avec `parentStructure`
    private Set<Structure> childStructures;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonIgnoreProperties("structures") // Remplace @JsonBackReference
    private Type type;

    @OneToMany(mappedBy = "structure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("structure-signataires")
    private Set<Signataire> signataires;


    // Getters and setters
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @PrePersist
    public void generateCode() {
        if (this.code == null || this.code.isEmpty()) {
            this.code = "STR" + System.currentTimeMillis();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelleAr() {
        return libelleAr;
    }

    public void setLibelleAr(String libelleAr) {
        this.libelleAr = libelleAr;
    }

    public String getLibelleFr() {
        return libelleFr;
    }

    public void setLibelleFr(String libelleFr) {
        this.libelleFr = libelleFr;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Set<DBUser> getUsers() {
        return users;
    }

    public void setUsers(Set<DBUser> users) {
        this.users = users;
    }

    public Structure getParentStructure() {
        return parentStructure;
    }

    public void setParentStructure(Structure parentStructure) {
        this.parentStructure = parentStructure;
    }

    public Set<Structure> getChildStructures() {
        return childStructures;
    }

    public void setChildStructures(Set<Structure> childStructures) {
        this.childStructures = childStructures;
    }
}