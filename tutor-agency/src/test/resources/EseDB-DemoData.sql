SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

DELETE FROM member_university;
DELETE FROM subscription;
DELETE FROM offer;
DELETE FROM course;
DELETE FROM member;
DELETE FROM university;

COMMIT;

INSERT INTO `University` (`id`, `name`) VALUES
(1, 'Bern'),
(2, 'Basel'),
(3, 'Zürich'),
(4, 'Genf'),
(5, 'Fribourg'),
(6, 'Neuchatel');

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
(10, 'Zoologie', 3),
(11, 'Datenbanken', 5),
(12, 'Software Engineering', 5),
(13, 'Programmieren 1', 5),
(14, 'Programmieren 2', 5),
(15, 'Genetik', 4),
(16, 'Populationsgenetik', 6);

-- --------------------------------------------------------

INSERT INTO `Member` (`id`, `description`, `email`, `fee`, `firstName`, `isTutor`, `lastName`, `password`, `username`) VALUES
(1, 'Das ist eine Beschreibung von ese', 'ese@example.com', 0, 'ese', 0, 'ese', 'e00760db8ed95a90efbbdefa0d167e6a', 'ese'),
(2, 'Das ist eine Beschreibung von student', 'student@example.com', 0, 'student', 0, 'student', '5f4dcc3b5aa765d61d8327deb882cf99', 'student'),
(3, 'Das ist eine Beschreibung von Tutor1', 'tutor1@example.com', 20, 'Tutor1', 1, 'Tutor1', '0b2f99d74e41e808a5e3c0c50223ae7f', 'tutor1'),
(4, 'Das ist eine Beschreibung von Tutor2', 'tutor2@example.com', 30, 'Tutor2', 1, 'Tutor2', 'eb007e610d25769d69f081ebbb337b13', 'tutor2');

-- --------------------------------------------------------

INSERT INTO `Offer` (`id`,`tutor_id`, `course_id`, `grade`) VALUES
(1, 3, 1, 4.5),
(2, 3, 2, 5.5),
(3, 3, 3, 4.0),
(4, 3, 4, 4.5),
(5, 3, 5, 5.25),
(6, 4, 8, 4.5),
(7, 4, 9, 6.0),
(8, 4, 10, 4.5),
(9, 4, 6, 4.5),
(10,3, 7, 5.75),
(11,4, 1, 4.5);

-- --------------------------------------------------------

INSERT INTO `Member_University` (`Member_id`, `universityList_id`) VALUES
(3, 1),
(3, 2),
(4, 3);

-- ---------------------------------------------------------
INSERT INTO `Subscription` (`id`,`member_id`, `offer_id`,`accepted`) VALUES
(1, 1, 1, 0),
(2, 2, 5, 0),
(3, 3, 6, 1),
(4, 4, 2, 1),
(5, 1, 5, 1),
(6, 2, 9, 0),
(7, 4, 3, 0),
(8, 1, 8, 0),
(9, 2, 1, 1);

COMMIT;