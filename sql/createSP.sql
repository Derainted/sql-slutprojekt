-- use movies;

DROP PROCEDURE IF EXISTS add_actor;
DELIMITER $$
CREATE PROCEDURE add_actor (IN actor_first_name varchar(50), IN actor_last_name varchar(50))
BEGIN
insert 
into actors (actor_first_name, actor_last_name)
value (@first_name, @last_name);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS add_movie;
DELIMITER $$
CREATE PROCEDURE add_movie (IN movie_titel varchar(100), IN release_date date, runtime INT)
BEGIN
insert into movies
(movie_titel, release_date, runtime)
values (@movie_titel, @release_date , @runtime);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS add_director;
DELIMITER $$
CREATE PROCEDURE add_director (IN dir_first_name varchar(50), IN dir_last_name varchar(50))
BEGIN
insert 
into directors (dir_first_name, dir_last_name)
value (@dir_first_name, @dir_last_name);
END$$
DELIMITER ;