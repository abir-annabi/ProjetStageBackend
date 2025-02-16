package com.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.app.models.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}