package com.cgl.cgladherents.repository;

import com.cgl.cgladherents.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
     public Utilisateur findByEmail(String email);
}
