DROP DATABASE IF EXISTS movies;
CREATE DATABASE IF NOT EXISTS movies;

USE movies;

DROP TABLE IF EXISTS movies;
CREATE TABLE IF NOT EXISTS movies (
movie_id INT AUTO_INCREMENT,
movie_titel VARCHAR(100) NOT NULL,
release_year INT NOT NULL,
runtime INT NOT NULL,
PRIMARY KEY (movie_id));

DROP TABLE IF EXISTS directors;
CREATE TABLE IF NOT EXISTS directors (
dir_id INT AUTO_INCREMENT,
dir_first_name varchar(50) NOT NULL,
dir_last_name varchar(50) NOT NULL,
PRIMARY KEY (dir_id)
);

DROP TABLE IF EXISTS movie_directors;
CREATE TABLE IF NOT EXISTS movie_directors (
dir_id INT NOT NULL,
movie_id INT NOT NULL,
FOREIGN KEY (dir_id) REFERENCES directors (dir_id),
FOREIGN KEY (movie_id) REFERENCES movies (movie_id)
);

DROP TABLE IF EXISTS genres;
CREATE TABLE IF NOT EXISTS genres (
gen_id INT AUTO_INCREMENT,
gen_titel VARCHAR(50) NOT NULL,
PRIMARY KEY (gen_id)
);

DROP TABLE IF EXISTS movie_genres;
CREATE TABLE IF NOT EXISTS movie_genres (
movie_id INT NOT NULL,
gen_id INT NOT NULL,
FOREIGN KEY (gen_id) REFERENCES genres (gen_id),
FOREIGN KEY (movie_id) REFERENCES movies (movie_id)
);

DROP TABLE IF EXISTS actors;
CREATE TABLE IF NOT EXISTS actors (
actor_id INT AUTO_INCREMENT,
actor_first_name VARCHAR(50) NOT NULL,
actor_last_name VARCHAR(50) NOT NULL,
PRIMARY KEY (actor_id)
);

DROP TABLE IF EXISTS movie_cast;
CREATE TABLE IF NOT EXISTS movie_cast (
actor_id INT NOT NULL,
movie_id INT NOT NULL,
roles varchar(50) NOT NULL,
FOREIGN KEY (actor_id) REFERENCES actors (actor_id),
FOREIGN KEY (movie_id) REFERENCES movies (movie_id)
);

DROP TABLE IF EXISTS audit;
CREATE TABLE IF NOT EXISTS actors_audit(
contact_id INT,
deleted_date DATE,
deleted_by VARCHAR(20)
);