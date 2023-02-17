package com.cgl.cgladherents.service;

import com.cgl.cgladherents.entity.Utilisateur;
import com.cgl.cgladherents.repository.UtilisateurRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public boolean verifierMotDePasse(String email, String motDePasse) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur != null) {
            String motDePasseHash = utilisateur.getMot_de_passe();
            System.out.println(motDePasseHash);
            boolean checkpw = BCrypt.checkpw(motDePasse, motDePasseHash);
            System.out.println("cest bon c'est les memes");
            return checkpw;
        } else {
            return false;
        }
    }
}
