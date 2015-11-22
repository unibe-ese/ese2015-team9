SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

DELETE FROM member_university;
DELETE FROM offer_member;
DELETE FROM offer;
DELETE FROM course;
DELETE FROM member;
DELETE FROM university;

COMMIT;

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

-- --------------------------------------------------------

INSERT INTO `Member` (`id`, `email`, `fee`, `firstName`, `isActivated`, `isTutor`, `lastName`, `password`, `username`) VALUES
(1, 'ese@example.com', 0, 'ese', 0, 0, 'ese', 'e00760db8ed95a90efbbdefa0d167e6a', 'ese'),
(2, 'student@example.com', 0, 'student', 0, 0, 'student', '5f4dcc3b5aa765d61d8327deb882cf99', 'student'),
(3, 'tutor1@example.com', 20, 'Tutor1', 0, 1, 'Tutor1', '0b2f99d74e41e808a5e3c0c50223ae7f', 'tutor1'),
(4, 'tutor2@example.com', 30, 'Tutor2', 1, 1, 'Tutor2', 'eb007e610d25769d69f081ebbb337b13', 'tutor2');

-- --------------------------------------------------------

INSERT INTO `Offer` (`tutor_id`, `course_id`, `grade`) VALUES
(3, 1, 4.5),
(3, 2, 5.5),
(3, 3, 4.0),
(3, 4, 4.5),
(3, 5, 5.25),
(4, 8, 4.5),
(4, 9, 6.0),
(4, 10, 4.5),
(4, 6, 4.5),
(3, 7, 5.75),
(4, 1, 4.5);

-- --------------------------------------------------------

INSERT INTO `Member_University` (`Member_id`, `universityList_id`) VALUES
(3, 1),
(3, 2),
(4, 3);

-- ---------------------------------------------------------
INSERT INTO `Offer_Member` (`subscriptions_id`, `subscribers_id`) VALUES
(11,1),
(10,2),
(9, 2),
(8, 2),
(1, 4),
(6, 3),
(5, 1),
(4, 1),
(3, 1);

COMMIT;