package com.cgl.cgladherents.service;

import com.cgl.cgladherents.entity.Adherent;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
    public class EmailJob {
    private final AdherentService adherentService;

    public EmailJob(AdherentService adherentService) {
        this.adherentService = adherentService;
    }
    @Scheduled(cron = "0 0 0 1 * *")
    public void sendEmails() throws MessagingException {
        List<Adherent> adherents = adherentService.getUtilisateurs();
        for (Adherent adherent : adherents) {
            Calendar cal = Calendar.getInstance();
            Date date_paiement_convertie = java.sql.Date.valueOf(adherent.getDate_renouvellement());
            cal.setTime(date_paiement_convertie);
            cal.add(Calendar.MONTH, -1);
            if (cal.getTime().before(new Date()) &&!adherent.isEmail_envoye() ) {
                sendEmail(adherent.getEmail(), "Renouvellement de cotisation", "Votre cotisation arrive à échéance. Pensez-à la renouveller pour bénificier de nos services en vous rendant : https://www.lacgl.fr/. ");
                adherentService.setEmailSent(adherent);
            }
        }
    }
    public void sendEmail(String to, String subject,String body)throws MessagingException{

        // Configuration de la session
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        //Authentification
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("christophe.hatchikian32@gmail.com", "wdkxbftmmqvywhrm");
                    }
                });
        try {
            // Création du message
            MimeMessage message = new MimeMessage(session);
            String from="christophe.hatchikian32@gmail.com";
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            // Envoi du message
            Transport.send(message);
            System.out.println("Le message a été envoyé avec succès");
        } catch (AddressException ae) {
            System.out.println("Adresse invalide : " + ae.getMessage());
        } catch (MessagingException me) {
            System.out.println("Erreur lors de l'envoi du message : " + me.getMessage());
        }
    }
    }
    





