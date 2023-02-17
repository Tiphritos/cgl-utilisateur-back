package com.cgl.cgladherents.controller;

import com.cgl.cgladherents.entity.Adherent;
import com.cgl.cgladherents.service.EmailJob;
import com.cgl.cgladherents.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.List;

@RequestMapping(value = "api/adherents")
@RestController
public class AdherentController {
@Autowired
    private AdherentService adherentService;
@Autowired
    private EmailJob emailJob;
@PostConstruct
public void init() throws MessagingException {
    emailJob.sendEmails();
}
    /*Liste des adhérents*/
    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<List<Adherent>> getAll() throws MessagingException {
        return ResponseEntity.ok(adherentService.getUtilisateurs());
    }

    /*Créer un nouvel adhérent*/
    @PostMapping(path = "/ajouter",produces = "application/json")
    public void nouveauAdherent(@RequestBody Adherent adherent){
        adherent.setEmail_envoye(false);
        adherentService.nouveauUtilsateur(adherent);
    }

}
