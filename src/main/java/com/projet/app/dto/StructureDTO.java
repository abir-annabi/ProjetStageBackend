package com.projet.app.dto;

import java.util.Set;

import com.projet.app.models.DBUser;
import com.projet.app.models.Structure;
import com.projet.app.models.Type;

public class StructureDTO {
    private Long id;
    private String code;
    private String libelleAr;
    private String libelleFr;
    private String adresse;
    private Set<DBUser> users;
    private Type type;
    private StructureDTO parentStructure; // Récursion limitée

    public StructureDTO(Structure structure) {
        this.id = structure.getId();
        this.code = structure.getCode();
        this.libelleAr = structure.getLibelleAr();
        this.libelleFr = structure.getLibelleFr();
        this.adresse = structure.getAdresse();
        this.users = structure.getUsers();
        this.type = structure.getType();
        if (structure.getParentStructure() != null) {
            this.parentStructure = new StructureDTO(structure.getParentStructure()); // Récursion limitée
        }
    }
    
    // Getters et Setters
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public StructureDTO getParentStructure() {
        return parentStructure;
    }

    public void setParentStructure(StructureDTO parentStructure) {
        this.parentStructure = parentStructure;
    }

}

