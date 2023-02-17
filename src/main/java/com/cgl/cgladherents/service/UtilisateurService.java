package com.cgl.cgladherents.service;

import com.cgl.cgladherents.entity.Adherent;
import com.cgl.cgladherents.entity.Utilisateur;
import com.cgl.cgladherents.repository.UtilisateurRepository;
import com.mysql.cj.util.Util;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    /*Trouver tous les utilisateurs */
    public List<Utilisateur> getUtilisateurs(){
        List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurRepository.findAll().forEach(utilisateurs::add);
        return utilisateurs;
    }
    public Utilisateur findbyMail(String email){
        return utilisateurRepository.findByEmail(email);
    }
    public void nouveauUtilisateur(Utilisateur utilisateur){
        String mdp = utilisateur.getMot_de_passe();
        String mdpHash = BCrypt.hashpw(mdp,BCrypt.gensalt());
        utilisateur.setMot_de_passe(mdpHash);
        utilisateurRepository.save(utilisateur);
    }
}
