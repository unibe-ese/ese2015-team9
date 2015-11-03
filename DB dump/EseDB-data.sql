-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 29, 2015 at 05:53 PM
-- Server version: 5.6.14
-- PHP Version: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `EseDB`
--

--
-- Dumping data for table `Course`
--
--
-- Dumping data for table `University`
--

INSERT INTO `University` (`id`, `name`) VALUES
(1, 'Bern'),
(2, 'Basel'),
(3, 'Zürich');

INSERT INTO `Course` (`id`, `name`, `university_id`) VALUES
(1, 'Datenbanken', 1),
(2, 'Software Engineering', 1),
(3, 'Programmieren 1', 1),
(4, 'Programmieren 2', 1),
(5, 'Netzwerke', 2),
(6, 'Genetik', 2),
(7, 'Populationsgenetik', 2),
(8, 'Kunstgeschichte: Methoden der Interpretation', 3),
(9, 'Einführung in die Geschichte der Kunstgeschichte', 3),
(10, 'Zoologie', 3);



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
