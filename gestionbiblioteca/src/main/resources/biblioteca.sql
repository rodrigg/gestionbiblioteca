-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2016 at 10:15 PM
-- Server version: 5.6.26
-- PHP Version: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biblioteca`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`konectia`@`localhost` PROCEDURE `insertejemplar`(IN `editorial` VARCHAR(150), IN `npaginas` VARCHAR(150), IN `libro_id` INT, OUT `id_ejemplar` INT)
    NO SQL
BEGIN
INSERT INTO ejemplares(`editorial`, `npaginas`, `libro_id`) VALUES (editorial,npaginas,libro_id);
SET id_ejemplar= LAST_INSERT_ID();
END$$

CREATE DEFINER=`konectia`@`localhost` PROCEDURE `insertlibro`(IN `titulo` VARCHAR(150), IN `autor` VARCHAR(150), IN `isbn` VARCHAR(150), OUT `id_libro` INT)
    NO SQL
BEGIN
INSERT INTO libros(`titulo`, `autor`, `isbn`) VALUES (titulo,autor,isbn);
SET id_libro = LAST_INSERT_ID();
END$$

CREATE DEFINER=`konectia`@`localhost` PROCEDURE `insertusuario`(IN `nombre` VARCHAR(150), IN `apellidos` VARCHAR(150), IN `password` VARCHAR(150), IN `fnacimiento` DATE, IN `email` VARCHAR(150), OUT `id_usuario` INT)
    NO SQL
BEGIN
INSERT INTO usuarios(`nombre`, `apellidos`, `password`,fnacimiento,email) VALUES (nombre,apellidos,password,fnacimiento,email);
SET id_usuario = LAST_INSERT_ID();
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `ejemplares`
--

CREATE TABLE IF NOT EXISTS `ejemplares` (
  `id` int(11) NOT NULL,
  `editorial` varchar(150) NOT NULL,
  `npaginas` int(11) NOT NULL,
  `libro_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ejemplares`
--

INSERT INTO `ejemplares` (`id`, `editorial`, `npaginas`, `libro_id`) VALUES
(0, 'No asignadas', 0, 0),
(1, 'Planeta', 150, 1),
(2, 'Espasa', 150, 3),
(4, 'No asignada', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `libros`
--

CREATE TABLE IF NOT EXISTS `libros` (
  `id` int(11) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `autor` varchar(150) NOT NULL,
  `isbn` varchar(150) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `libros`
--

INSERT INTO `libros` (`id`, `titulo`, `autor`, `isbn`) VALUES
(1, 'El quijote', 'Cervantes', '56d656-6569-6'),
(2, 'Lazarillo', 'Anonimo', 'dasads'),
(3, 'Celestina', 'Fernando de Rojas', 'dassda');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `fnacimiento` date NOT NULL,
  `email` varchar(150) NOT NULL,
  `ejemplar_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellidos`, `password`, `fnacimiento`, `email`, `ejemplar_id`) VALUES
(1, 'Alvare', 'dsasdadsa', 'adssdadsa', '2016-09-21', 'dsadsa', 1),
(2, 'Alvaroooo', 'adsa', 'adsads', '2016-09-21', 'adssad', 0),
(3, 'adsdas', 'adssad', 'dsasad', '2016-09-21', 'sdaasd', 0),
(4, 'Pakoo', 'asdsda', 'adsdsa', '2016-09-21', 'dsaads', 0),
(5, 'daasasd', 'dsaas', 'dsaasd', '2016-09-21', 'dsasad', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ejemplares`
--
ALTER TABLE `ejemplares`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `libros`
--
ALTER TABLE `libros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
