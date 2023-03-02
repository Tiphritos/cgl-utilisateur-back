-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : jeu. 02 mars 2023 à 09:00
-- Version du serveur : 10.10.2-MariaDB
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `donnees_utilisateurs`
--

-- --------------------------------------------------------

--
-- Structure de la table `adherents`
--

DROP TABLE IF EXISTS `adherents`;
CREATE TABLE IF NOT EXISTS `adherents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `date_paiement` date NOT NULL,
  `date_renouvellement` date DEFAULT NULL,
  `email_envoye` tinyint(1) DEFAULT 0,
  `adresse` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`id`, `nom`, `prenom`, `email`, `date_paiement`, `date_renouvellement`, `email_envoye`, `adresse`, `telephone`) VALUES
(1, 'Pierre', 'paul', 'efazfadx', '2023-02-14', '2024-02-14', 1, '1 rue de Nantes 44300 Nantes', '0648078965'),
(11, 'Dubois', 'Pierre', 'pierre.dubois@mail.com', '2022-03-01', '2023-03-01', 1, '3 place de la Mairie, 31000 Toulouse', '+33555555555'),
(10, 'Martin', 'Julie', 'julie.martin@mail.com', '2022-02-01', '2023-02-01', 1, '2 avenue des Roses, 13008 Marseille', '+33612345678'),
(9, 'Dupont', 'Jean', 'jean.dupont@mail.com', '2022-01-01', '2023-01-01', 1, '1 rue des Lilas, 75001 Paris', '+33123456789'),
(12, 'Lefebvre', 'Marie', 'marie.lefebvre@mail.com', '2022-04-01', '2023-04-01', 1, '4 rue des Champs, 69002 Lyon', '+33444444444'),
(13, 'Durand', 'Lucie', 'lucie.durand@mail.com', '2022-05-01', '2023-05-01', 1, '5 boulevard des Fleurs, 35000 Rennes', '+33222222222'),
(14, 'Moreau', 'Antoine', 'antoine.moreau@mail.com', '2022-06-01', '2023-06-01', 1, '6 avenue des Pins, 44000 Nantes', '+33111111111'),
(15, 'Girard', 'Sophie', 'sophie.girard@mail.com', '2022-07-01', '2023-07-01', 1, '7 rue des Orangers, 59000 Lille', '+33444444444'),
(16, 'Lopez', 'Thomas', 'thomas.lopez@mail.com', '2022-08-01', '2023-08-01', 1, '8 avenue des Lauriers, 13009 Marseille', '+33666666666'),
(17, 'Clerc', 'Camille', 'camille.clerc@mail.com', '2022-09-01', '2023-09-01', 1, '9 rue des Platanes, 69003 Lyon', '+33777777777'),
(18, 'Petit', 'Nicolas', 'nicolas.petit@mail.com', '2022-10-01', '2023-10-01', 1, '10 boulevard des Alouettes, 75020 Paris', '+33678901234'),
(25, 'efefa', 'efefefezfefz', 'efefe@zfezfze.com', '2023-02-15', '2024-02-15', 0, '464zfze', '0464864'),
(24, 'hatfcazca', 'dazdazdzadd', 'poulpou@yopmail.com', '2022-02-12', '2023-02-12', 1, '5846zdadfazf', '06490945'),
(23, '\"fe', 'fefeefzef', 'ezffefezfez@fzef', '2023-02-15', '2024-02-15', 1, 'ezf', '159894'),
(26, 'zdadazda', 'dazadzdadzzda', 'azdazdazdazd@azdazda', '2023-02-06', '2024-02-06', 0, 'zdazdz', '05684984'),
(27, 'e(rgr', 'efzefzefz', 'fezefzefzefefz@efzef', '2023-02-21', '2024-02-21', 0, 'efzezezefzfe', '48+6948946'),
(28, 'e(rgr', 'efzefzefz', 'fezefzefzefefz@efzef', '2023-02-21', '2024-02-21', 0, 'efzezezefzfe', '48+6948946'),
(29, 'y\'t\'\"\'t\"\'tf', 'fefzefzezfe', 'efzefzef@efzefzef', '2023-02-10', '2024-02-10', 0, 'efzefzeffefeeefze', '05486'),
(30, '&éd&', 'azdazdzada', 'zdazdaz@dza', '2023-02-09', '2024-02-09', 0, 'zzdazd', '4898485'),
(31, 'eezfzefze', 'efzfeefzeffze', 'efzefze@azazdadz', '2023-02-16', '2024-02-16', 0, '4789489', 'zdazdazedadfz');

--
-- Déclencheurs `adherents`
--
DROP TRIGGER IF EXISTS `update_departure_date`;
DELIMITER $$
CREATE TRIGGER `update_departure_date` BEFORE INSERT ON `adherents` FOR EACH ROW BEGIN
  SET NEW.date_renouvellement = DATE_ADD(NEW.date_paiement, INTERVAL 1 YEAR);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenom`, `email`, `mot_de_passe`, `role`) VALUES
(1, 'chrichri', 'toto', 'chrichri.toto@gmail.com', '$2a$10$LAXKntO/mQvPp1gUyvuDz.xC1PEWk0nzxumNW3Gc9gOmJdo088wQa', 'admin'),
(2, 'test', 'test', 'testemail', '$2a$10$r6MSzHa4nSw.7/VYlmWI4eZGUdCJXQnO3ntS3YFmcm5RXF1EbAe1O', 'admin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
