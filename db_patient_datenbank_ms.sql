-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 28. Mai 2020 um 21:53
-- Server-Version: 10.4.11-MariaDB
-- PHP-Version: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `db_patient_datenbank_ms`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbl_appointment`
--

CREATE TABLE `tbl_appointment` (
  `_id` int(11) NOT NULL,
  `user_id_doctor` int(11) NOT NULL,
  `user_id_patient` int(11) NOT NULL,
  `appointment_date` text COLLATE utf8_german2_ci DEFAULT NULL,
  `time` text COLLATE utf8_german2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `tbl_appointment`
--

INSERT INTO `tbl_appointment` (`_id`, `user_id_doctor`, `user_id_patient`, `appointment_date`, `time`) VALUES
(1, 5, 8, '2020-05-30', '12:20'),
(2, 5, 9, '2020-06-04', '09:20'),
(3, 5, 10, '2020-05-21', '15:40'),
(4, 5, 11, '2020-05-09', '11:15'),
(5, 5, 10, '2020-06-01', '15:30'),
(6, 5, 8, '2021-06-23', '12:15'),
(8, 5, 10, '2020-05-30', '12:00'),
(9, 5, 12, '2020-05-14', '11:10'),
(11, 13, 9, '2020-04-30', '222'),
(12, 14, 12, '2020-05-08', '11:10'),
(13, 5, 15, '2020-06-02', '11:10'),
(14, 5, 12, '2020-06-26', '17:30'),
(15, 5, 16, '2020-06-05', '14:40'),
(16, 5, 8, '2020-06-06', '11:20'),
(17, 5, 9, '2020-06-06', '11:30'),
(18, 5, 16, '2020-06-01', '20:00');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbl_patient_records`
--

CREATE TABLE `tbl_patient_records` (
  `_id` int(11) NOT NULL,
  `date` text COLLATE utf8_german2_ci DEFAULT NULL,
  `weight` text COLLATE utf8_german2_ci DEFAULT NULL,
  `medical_h` text COLLATE utf8_german2_ci DEFAULT NULL,
  `disease` text COLLATE utf8_german2_ci DEFAULT NULL,
  `allergie` text COLLATE utf8_german2_ci DEFAULT NULL,
  `vaccination` text COLLATE utf8_german2_ci DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `tbl_patient_records`
--

INSERT INTO `tbl_patient_records` (`_id`, `date`, `weight`, `medical_h`, `disease`, `allergie`, `vaccination`, `user_id`) VALUES
(3, '26.05.2020 03:00:30', '>noValueYet<', 'noch nicht', 'noch nicht', '>noValueYet<', '>noValueYet<', 10),
(4, '26.05.2020 03:06:49', '>noValueYet<', '>noValueYet<', '>noValueYet<', 'Heuschnupfen Allergie', ' Masern Impfung', 11),
(5, '26.05.2020 04:01:25', '>noValueYet<', '>noValueYet<', 'Feber', '>noValueYet<', '>noValueYet<', 10),
(8, '27.05.2020 02:35:54', '>noValueYet<', '>noValueYet<', '>noValueYet<', '>noValueYet<', '>noValueYet<', 12),
(9, '28.05.2020 08:56:12', '65', 'Eine Bluterkrankung bei einem Familienmitglied', 'Akne', '>noValueYet<', '>noValueYet<', 15),
(10, '28.05.2020 09:05:02', '66', '>noValueYet<', '>noValueYet<', 'Arzneimittelallergie', '>noValueYet<', 15),
(11, '28.05.2020 11:59:43', '55', 'Diabetes', 'kein', 'Insektengiftallergie', 'kein', 16),
(12, '28.05.2020 12:01:45', '60', 'kein', 'kein', 'kein', 'Masern', 16),
(13, '28.05.2020 12:15:07', '50', 'kein', 'kein', 'kein', 'Diphtherie', 17);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbl_user`
--

CREATE TABLE `tbl_user` (
  `_id` int(11) NOT NULL,
  `userName` text COLLATE utf8_german2_ci DEFAULT NULL,
  `userPw` text COLLATE utf8_german2_ci DEFAULT NULL,
  `firstName` text COLLATE utf8_german2_ci DEFAULT NULL,
  `lastName` text COLLATE utf8_german2_ci DEFAULT NULL,
  `birthDate` text COLLATE utf8_german2_ci DEFAULT NULL,
  `userRole` text COLLATE utf8_german2_ci DEFAULT NULL,
  `email` text COLLATE utf8_german2_ci DEFAULT NULL,
  `street` text COLLATE utf8_german2_ci DEFAULT NULL,
  `houseNumber` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `tbl_user`
--

INSERT INTO `tbl_user` (`_id`, `userName`, `userPw`, `firstName`, `lastName`, `birthDate`, `userRole`, `email`, `street`, `houseNumber`) VALUES
(4, 'Jojo', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Jojo', 'ddd', '>noValueYet<', 'Doctor', '>noValueYet<', '>noValueYet<', -1),
(5, 'Walaa', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Walaa', 'SSS', '>noValueYet<', 'Doctor', 'walaa@gmail.com', '>noValueYet<', -1),
(6, 'Sara', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Sara', 'SSS', '>noValueYet<', 'Doctor', 'sara@fff.de', '>noValueYet<', -1),
(8, 'Bana', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Bana', 'BBB', '>noValueYet<', 'PATIENT', '>noValueYet<', '>noValueYet<', -1),
(9, 'Rema', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Rema', 'RRR', '>noValueYet<', 'PATIENT', '>noValueYet<', '>noValueYet<', -1),
(10, 'Rana', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Rana', 'RRR', '>noValueYet<', 'PATIENT', '>noValueYet<', '>noValueYet<', -1),
(11, 'Gena', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Gena', 'GGG', '>noValueYet<', 'PATIENT', '>noValueYet<', '>noValueYet<', -1),
(12, 'Juna', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Juna', 'UUU', '>noValueYet<', 'PATIENT', '>noValueYet<', '>noValueYet<', -1),
(13, 'Tala', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Tala', 'TTT', '>noValueYet<', 'Doctor', 'Tala@fff.de', '>noValueYet<', -1),
(14, 'Dana', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Dana', 'DDD', '>noValueYet<', 'Doctor', 'Dana@ddd.de', '>noValueYet<', -1),
(15, 'Alaa', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Alaa', 'AAA', '>noValueYet<', 'PATIENT', '>noValueYet<', '>noValueYet<', -1),
(16, 'Evaa', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Evaa', 'EEE', '>noValueYet<', 'PATIENT', '>noValueYet<', '>noValueYet<', -1),
(17, 'Irena', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Irena', 'III', '>noValueYet<', 'PATIENT', '>noValueYet<', '>noValueYet<', -1),
(18, 'Ben', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Ben', 'BBB', '>noValueYet<', 'Doctor', 'Ben@ddfd.com', '>noValueYet<', -1),
(19, 'Sami', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Sami', 'SSS', '>noValueYet<', 'Doctor', 'Sami@fff.de', '>noValueYet<', -1);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `tbl_appointment`
--
ALTER TABLE `tbl_appointment`
  ADD PRIMARY KEY (`_id`),
  ADD KEY `user_id_doctor` (`user_id_doctor`),
  ADD KEY `user_id_patient` (`user_id_patient`);

--
-- Indizes für die Tabelle `tbl_patient_records`
--
ALTER TABLE `tbl_patient_records`
  ADD PRIMARY KEY (`_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indizes für die Tabelle `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `tbl_appointment`
--
ALTER TABLE `tbl_appointment`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT für Tabelle `tbl_patient_records`
--
ALTER TABLE `tbl_patient_records`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT für Tabelle `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `tbl_appointment`
--
ALTER TABLE `tbl_appointment`
  ADD CONSTRAINT `tbl_appointment_ibfk_1` FOREIGN KEY (`user_id_doctor`) REFERENCES `tbl_user` (`_id`),
  ADD CONSTRAINT `tbl_appointment_ibfk_2` FOREIGN KEY (`user_id_patient`) REFERENCES `tbl_user` (`_id`);

--
-- Constraints der Tabelle `tbl_patient_records`
--
ALTER TABLE `tbl_patient_records`
  ADD CONSTRAINT `tbl_patient_records_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
