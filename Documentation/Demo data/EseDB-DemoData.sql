-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 10, 2015 at 08:29 PM
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

-- --------------------------------------------------------
--
-- Table structure for table `University`
--

CREATE TABLE IF NOT EXISTS `University` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `University`
--

INSERT INTO `University` (`id`, `name`) VALUES
(1, 'Bern'),
(2, 'Basel'),
(3, 'Zürich');

--
-- Table structure for table `Course`
--

CREATE TABLE IF NOT EXISTS `Course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `university_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK78A7CC3B3FE0A6E9` (`university_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `Course`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `Member`
--

CREATE TABLE IF NOT EXISTS `Member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `isActivated` tinyint(1) NOT NULL,
  `isTutor` tinyint(1) NOT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `Member`
--

INSERT INTO `Member` (`id`, `email`, `fee`, `firstName`, `isActivated`, `isTutor`, `lastName`, `password`, `username`) VALUES
(1, 'ese@example.com', 0, 'ese', 0, 0, 'ese', 'e00760db8ed95a90efbbdefa0d167e6a', 'ese'),
(2, 'student@example.com', 0, 'student', 0, 0, 'student', '5f4dcc3b5aa765d61d8327deb882cf99', 'student'),
(3, 'tutor1@example.com', 20, 'Tutor1', 0, 1, 'Tutor1', '0b2f99d74e41e808a5e3c0c50223ae7f', 'tutor1'),
(4, 'tutor2@example.com', 30, 'Tutor2', 1, 1, 'Tutor2', 'eb007e610d25769d69f081ebbb337b13', 'tutor2');

-- --------------------------------------------------------

--
-- Table structure for table `Member_Course`
--

CREATE TABLE IF NOT EXISTS `Member_Course` (
  `members_id` bigint(20) NOT NULL,
  `courseList_id` bigint(20) NOT NULL,
  KEY `FK8B5A2D60CEF0A706` (`courseList_id`),
  KEY `FK8B5A2D6026178A84` (`members_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Member_Course`
--

INSERT INTO `Member_Course` (`members_id`, `courseList_id`) VALUES
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(4, 8),
(4, 9),
(4, 10),
(4, 6),
(3, 7),
(4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Member_University`
--

CREATE TABLE IF NOT EXISTS `Member_University` (
  `Member_id` bigint(20) NOT NULL,
  `universityList_id` bigint(20) NOT NULL,
  KEY `FK44BE0D5352A1FAA6` (`universityList_id`),
  KEY `FK44BE0D5326178A84` (`Member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Member_University`
--

INSERT INTO `Member_University` (`Member_id`, `universityList_id`) VALUES
(3, 1),
(3, 2),
(4, 3);

-- --------------------------------------------------------


--
-- Constraints for dumped tables
--

--
-- Constraints for table `Course`
--
ALTER TABLE `Course`
  ADD CONSTRAINT `FK78A7CC3B3FE0A6E9` FOREIGN KEY (`university_id`) REFERENCES `University` (`id`);

--
-- Constraints for table `Member_Course`
--
ALTER TABLE `Member_Course`
  ADD CONSTRAINT `FK8B5A2D6026178A84` FOREIGN KEY (`members_id`) REFERENCES `Member` (`id`),
  ADD CONSTRAINT `FK8B5A2D60CEF0A706` FOREIGN KEY (`courseList_id`) REFERENCES `Course` (`id`);

--
-- Constraints for table `Member_University`
--
ALTER TABLE `Member_University`
  ADD CONSTRAINT `FK44BE0D5326178A84` FOREIGN KEY (`Member_id`) REFERENCES `Member` (`id`),
  ADD CONSTRAINT `FK44BE0D5352A1FAA6` FOREIGN KEY (`universityList_id`) REFERENCES `University` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
