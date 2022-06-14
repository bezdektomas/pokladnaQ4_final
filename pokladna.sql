-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1:3306
-- Vytvořeno: Sob 12. čen 2021, 19:36
-- Verze serveru: 8.0.21
-- Verze PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `pokladna`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `kategorie`
--

DROP TABLE IF EXISTS `kategorie`;
CREATE TABLE IF NOT EXISTS `kategorie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nazev` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Vypisuji data pro tabulku `kategorie`
--

INSERT INTO `kategorie` (`id`, `nazev`) VALUES
(1, 'Menu'),
(2, 'Menu - Příloha'),
(3, 'Menu - Nápoj'),
(4, 'Menu - Přídavek'),
(5, 'Burgery'),
(6, 'Burgery - Přídavek'),
(7, 'Nápoje'),
(8, 'Nápoje - Přídavek'),
(9, 'Ostatní');

-- --------------------------------------------------------

--
-- Struktura tabulky `objednavka`
--

DROP TABLE IF EXISTS `objednavka`;
CREATE TABLE IF NOT EXISTS `objednavka` (
  `idObjednavky` int NOT NULL AUTO_INCREMENT,
  `cas` time DEFAULT NULL,
  `datum` date NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`idObjednavky`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Vypisuji data pro tabulku `objednavka`
--

INSERT INTO `objednavka` (`idObjednavky`, `cas`, `datum`, `status`) VALUES
(3, '21:22:35', '2021-05-29', 0),
(4, '21:26:35', '2021-05-29', 0),
(6, '21:37:04', '2021-05-29', 4),
(7, '21:37:43', '2021-05-29', 4),
(8, '21:38:03', '2021-05-29', 1),
(9, '22:21:22', '2021-06-05', 1),
(11, '22:21:22', '2021-06-05', 1),
(12, '22:21:22', '2021-06-05', 4),
(13, '22:11:46', '2021-06-10', 4),
(14, '22:13:30', '2021-06-10', 4),
(15, '22:14:24', '2021-06-10', 4),
(16, '22:14:52', '2021-06-10', 4),
(17, '22:22:18', '2021-06-10', 4),
(18, '22:22:28', '2021-06-10', 2),
(19, '22:26:46', '2021-06-10', 4),
(20, '19:30:45', '2021-06-12', 1),
(21, '19:59:22', '2021-06-12', 3);

-- --------------------------------------------------------

--
-- Struktura tabulky `polozka`
--

DROP TABLE IF EXISTS `polozka`;
CREATE TABLE IF NOT EXISTS `polozka` (
  `idPolozky` int NOT NULL AUTO_INCREMENT,
  `kategorie_id` int NOT NULL,
  `nazevPolozky` varchar(45) NOT NULL,
  `cena` int NOT NULL,
  `aktivni` tinyint(1) NOT NULL,
  PRIMARY KEY (`idPolozky`),
  KEY `fk_polozka_kategorie1_idx` (`kategorie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Vypisuji data pro tabulku `polozka`
--

INSERT INTO `polozka` (`idPolozky`, `kategorie_id`, `nazevPolozky`, `cena`, `aktivni`) VALUES
(1, 9, 'Taštička', 29, 1),
(2, 9, 'Sladkokyselá omáčka', 10, 1),
(3, 9, 'Kari omáčka', 10, 1),
(4, 1, 'Kebab', 110, 1),
(5, 9, 'Sýrová omáčka', 15, 1),
(6, 9, 'Kečup', 5, 1),
(7, 7, 'CocaCola', 39, 1),
(8, 7, 'Fanta', 39, 1),
(9, 7, 'Sprite', 39, 1),
(10, 7, 'Malinovka', 33, 1),
(11, 7, 'Lipton', 39, 1),
(12, 7, 'Voda', 29, 1),
(13, 7, 'Vinea', 39, 1),
(14, 5, 'Hamburger', 33, 1),
(15, 5, 'Cheeseburger', 33, 1),
(16, 5, 'Tasty Chicken', 39, 1),
(17, 5, 'Tasty Cheese', 39, 1),
(18, 5, 'Big Mac', 89, 1),
(19, 5, 'Vedží burgr', 69, 1),
(20, 6, 'Bez okurky', 0, 1),
(21, 6, 'Slanina', 10, 1),
(22, 6, 'Cibule', 5, 1),
(23, 6, 'Sýr', 15, 1),
(24, 8, 'Bez ledu', 0, 1),
(25, 1, 'Big Mac', 129, 1),
(26, 1, 'McChicken', 129, 1),
(27, 1, 'McRoyal', 129, 1),
(29, 1, '6 ks McNugets', 129, 1),
(30, 1, 'West Coast Baken', 169, 1),
(31, 2, 'Hranolky', 0, 1),
(32, 2, 'Salát', 0, 1),
(33, 3, 'CocaCola', 0, 1),
(34, 3, 'Fanta', 0, 1),
(35, 3, 'Sprite', 0, 1),
(36, 3, 'Malinovka', 0, 1),
(37, 4, 'Sladkokyselá omáčka', 10, 1),
(38, 4, 'Kečup', 6, 1),
(39, 4, 'Taštička', 29, 1),
(40, 4, 'Sýrové kroužky', 39, 1),
(42, 9, 'Nanuk', 30, 0),
(44, 7, 'Diplomatico', 999, 1),
(45, 4, 'Tatarka', 0, 1);

-- --------------------------------------------------------

--
-- Struktura tabulky `polozka_has_objednavka`
--

DROP TABLE IF EXISTS `polozka_has_objednavka`;
CREATE TABLE IF NOT EXISTS `polozka_has_objednavka` (
  `idPolozky` int NOT NULL,
  `idObjednavky` int NOT NULL,
  `poradiVObjednavce` int NOT NULL,
  `prirazeniPolozky` int NOT NULL,
  PRIMARY KEY (`idPolozky`,`idObjednavky`,`poradiVObjednavce`),
  KEY `fk_polozka_has_objednavka_objednavka1_idx` (`idObjednavky`),
  KEY `fk_polozka_has_objednavka_polozka1_idx` (`idPolozky`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Vypisuji data pro tabulku `polozka_has_objednavka`
--

INSERT INTO `polozka_has_objednavka` (`idPolozky`, `idObjednavky`, `poradiVObjednavce`, `prirazeniPolozky`) VALUES
(1, 14, 7, 7),
(1, 18, 1, 1),
(13, 14, 6, 6),
(15, 20, 1, 1),
(16, 7, 1, 1),
(17, 6, 1, 1),
(17, 14, 3, 3),
(17, 21, 1, 1),
(18, 12, 1, 1),
(18, 16, 4, 4),
(20, 12, 2, 1),
(20, 16, 5, 4),
(20, 20, 2, 1),
(21, 7, 2, 1),
(21, 14, 4, 3),
(21, 16, 6, 4),
(21, 20, 3, 1),
(21, 21, 2, 1),
(22, 14, 5, 3),
(22, 16, 7, 4),
(22, 20, 4, 1),
(22, 21, 3, 1),
(23, 16, 8, 4),
(23, 20, 5, 1),
(24, 15, 2, 1),
(26, 4, 1, 1),
(27, 14, 1, 1),
(28, 12, 3, 3),
(30, 13, 1, 1),
(30, 13, 2, 2),
(31, 12, 4, 3),
(31, 13, 3, 2),
(31, 17, 2, 1),
(32, 4, 2, 1),
(32, 14, 2, 1),
(33, 12, 5, 3),
(33, 13, 4, 2),
(35, 4, 3, 1),
(35, 17, 3, 1),
(37, 12, 6, 3),
(38, 12, 7, 3),
(38, 13, 5, 2),
(39, 12, 8, 3),
(40, 12, 9, 3),
(40, 17, 4, 1),
(41, 17, 6, 6),
(42, 19, 1, 1),
(43, 17, 1, 1),
(44, 15, 1, 1),
(45, 4, 4, 1),
(45, 12, 10, 3),
(45, 17, 5, 1);

--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `polozka`
--
ALTER TABLE `polozka`
  ADD CONSTRAINT `fk_polozka_kategorie1` FOREIGN KEY (`kategorie_id`) REFERENCES `kategorie` (`id`);

--
-- Omezení pro tabulku `polozka_has_objednavka`
--
ALTER TABLE `polozka_has_objednavka`
  ADD CONSTRAINT `fk_polozka_has_objednavka_objednavka1` FOREIGN KEY (`idObjednavky`) REFERENCES `objednavka` (`idObjednavky`),
  ADD CONSTRAINT `fk_polozka_has_objednavka_polozka1` FOREIGN KEY (`idPolozky`) REFERENCES `polozka` (`idPolozky`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
