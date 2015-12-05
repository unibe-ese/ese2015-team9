
SET time_zone = "+00:00";

DELETE FROM Member_University;
DELETE FROM Subscription;
DELETE FROM Offer;
DELETE FROM Course;
DELETE FROM Member;
DELETE FROM University;

COMMIT;

INSERT INTO `University` (`id`, `name`) VALUES
(1, 'Bern'),
(2, 'Luzern');

INSERT INTO `Course` (`id`, `name`, `university_id`) VALUES
(1, 'Databases', 1),
(2, 'Datastructures', 1),
(3, 'Databases', 2);

-- --------------------------------------------------------

INSERT INTO `Member` (`id`, `description`, `email`, `fee`, `firstName`, `isTutor`, `lastName`, `password`, `username`) VALUES
(1, 'Das ist eine Beschreibung von Bob', 'bob@example.com', 40, 'Bob', 1, 'B.', '5f4dcc3b5aa765d61d8327deb882cf99', 'bob'),
(2, 'Das ist eine Beschreibung von Eve', 'eve@example.com', 30, 'Eve', 1, 'E.', '5f4dcc3b5aa765d61d8327deb882cf99', 'eve');

-- --------------------------------------------------------

INSERT INTO `Offer` (`id`,`tutor_id`, `course_id`, `grade`) VALUES
(1, 1, 1, 4.0),
(2, 1, 2, 5.5),
(3, 2, 3, 5.0); 

-- --------------------------------------------------------

INSERT INTO `Member_University` (`Member_id`, `universityList_id`) VALUES
(1, 1),
(2, 2);

-- ---------------------------------------------------------
INSERT INTO `Subscription` (`id`,`member_id`, `offer_id`,`accepted`) VALUES
(1, 2, 1, 0),  
(2, 1, 3, 0); 


COMMIT;