package com.cgl.cgladherents.controller;

import com.cgl.cgladherents.entity.Adherent;
import com.cgl.cgladherents.entity.Utilisateur;
import com.cgl.cgladherents.service.AuthService;
import com.cgl.cgladherents.service.UtilisateurService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping(value = "api/utilisateurs")
@RestController
public class UtilisateurController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UtilisateurService utilisateurService;

    /*Liste des utilisateurs*/
    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<List<Utilisateur>> getAll() throws MessagingException {
        return ResponseEntity.ok(utilisateurService.getUtilisateurs());
    }
    @PostMapping(path = "/ajouter",produces = "application/json")
    public void nouveauUtilisateur(@RequestBody Utilisateur utilisateur){
        utilisateurService.nouveauUtilisateur(utilisateur);}

    //Pour la connexion
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public Utilisateur login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        System.out.println("email : "+ email +" " +  " mot de passe : " + password);
          Utilisateur utilisateur = utilisateurService.findbyMail(email);
        if (utilisateur!=null && authService.verifierMotDePasse(email, password)) {
            session.setAttribute("email", email);
            return utilisateur;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou mot de passe incorrect");
        }
    }

}
