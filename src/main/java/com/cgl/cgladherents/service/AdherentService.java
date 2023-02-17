package com.cgl.cgladherents.service;

import com.cgl.cgladherents.entity.Adherent;
import com.cgl.cgladherents.repository.AdherentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdherentService {
    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private Environment env;
    /*Voir tous les adhérents*/
    public List<Adherent> getUtilisateurs(){
        List<Adherent> adherents = new ArrayList<>();
        adherentRepository.findAll().forEach(adherents::add);
        return adherents;
    }
    /*Ajouter un utilisateur*/
    public void nouveauUtilsateur(Adherent adherent){
        adherentRepository.save(adherent);
    }

    /*Mise à jour de l'envoi de mail */
    public void setEmailSent(@NotNull Adherent adherent) {
        // Mettre à jour le champ emailSent pour l'utilisateur donné
        String sql = "UPDATE adherents SET email_envoye = true WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setLong(1, adherent.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private Connection getConnection() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            // Traiter les exceptions SQL
        }
        return connection;
    }
}
