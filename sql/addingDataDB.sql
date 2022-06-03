use movies;

-- movies
insert into movies (movie_titel, release_year, runtime) values ('Greedy', '1992', 70);
insert into movies (movie_titel, release_year, runtime) values ('Grey', '1974', 190);
insert into movies (movie_titel, release_year, runtime) values ('Freezby', '2025', 120);
insert into movies (movie_titel, release_year, runtime) values ('The best', '1900', 120);
insert into movies (movie_titel, release_year, runtime) values ('Hygiene', '2010', 200);
insert into movies (movie_titel, release_year, runtime) values ('GRovt', '1943', 111);
insert into movies (movie_titel, release_year, runtime) values ('Hey friends', '1912', 32);
insert into movies (movie_titel, release_year, runtime) values ('The first one', '1999', 122);
insert into movies (movie_titel, release_year, runtime) values ('Vikings', '1993', 145);
insert into movies (movie_titel, release_year, runtime) values ('Tell me', '2025', 442);

-- genres
insert into genres (gen_titel) values ('Action');
insert into genres (gen_titel) values ('Sci-fi');
insert into genres (gen_titel) values ('Imaginary');
insert into genres (gen_titel) values ('Booze');
insert into genres (gen_titel) values ('Sci-Fi');

-- genres to movies
insert into movie_genres (movie_id, gen_id) values (1, 2);
insert into movie_genres (movie_id, gen_id) values (2, 3);
insert into movie_genres (movie_id, gen_id) values (3, 3);
insert into movie_genres (movie_id, gen_id) values (2, 2);
insert into movie_genres (movie_id, gen_id) values (3, 3);
insert into movie_genres (movie_id, gen_id) values (2, 7);
insert into movie_genres (movie_id, gen_id) values (5, 4);
insert into movie_genres (movie_id, gen_id) values (6, 3);
insert into movie_genres (movie_id, gen_id) values (7, 3);
insert into movie_genres (movie_id, gen_id) values (7, 1);
insert into movie_genres (movie_id, gen_id) values (9, 7);
insert into movie_genres (movie_id, gen_id) values (8, 2);
insert into movie_genres (movie_id, gen_id) values (7, 7);
insert into movie_genres (movie_id, gen_id) values (10, 3);


-- directors
insert into directors (dir_first_name, dir_last_name) value ('Tina', 'Jimmy');
insert into directors (dir_first_name, dir_last_name) value ('Ali', 'Haddy');
insert into directors (dir_first_name, dir_last_name) value ('Saul', 'Panda');
insert into directors (dir_first_name, dir_last_name) value ('Jimmyy', 'Gillion');
insert into directors (dir_first_name, dir_last_name) value ('Batman', 'Supp');
insert into directors (dir_first_name, dir_last_name) value ('Hollow', 'Been');
insert into directors (dir_first_name, dir_last_name) value ('Charlie', 'Hamz');
insert into directors (dir_first_name, dir_last_name) value ('Hanna', 'Kitty');
insert into directors (dir_first_name, dir_last_name) value ('Linda', 'Lil');
insert into directors (dir_first_name, dir_last_name) value ('Lina', 'Booze');

-- actors
insert into actors (actor_first_name, actor_last_name)value ('Lilly', 'Tulip');
insert into actors (actor_first_name, actor_last_name)value ('Broski', 'tupac');
insert into actors (actor_first_name, actor_last_name) value ('Broove', 'Honey');
insert into actors (actor_first_name, actor_last_name) value ('Que', 'Si');
insert into actors (actor_first_name, actor_last_name) value ('Brea', 'Grim');
insert into actors (actor_first_name, actor_last_name) value ('Tina', 'Groob');
insert into actors (actor_first_name, actor_last_name) value ('Daw', 'Oxfo');
insert into actors (actor_first_name, actor_last_name) value ('Hanny', 'Breem');
insert into actors (actor_first_name, actor_last_name) value ('Alex', 'Bloob');
insert into actors (actor_first_name, actor_last_name) value ('Aley', 'Mo');

-- actors to cast
insert into movie_cast (actor_id, movie_id, roles) VALUES (1, 2, 'Creepy tree');
insert into movie_cast (actor_id, movie_id, roles) VALUES (2, 4, 'Dog #4');
insert into movie_cast (actor_id, movie_id, roles) VALUES (3, 5, 'Ben Grürt');
insert into movie_cast (actor_id, movie_id, roles) VALUES (4, 7, 'Background actor #43');
insert into movie_cast (actor_id, movie_id, roles) VALUES (5, 4, 'Hans Rita');
insert into movie_cast (actor_id, movie_id, roles) VALUES (6, 2, 'Xena X');
insert into movie_cast (actor_id, movie_id, roles) VALUES (7, 5, 'The ghost');
insert into movie_cast (actor_id, movie_id, roles) VALUES (8, 1, 'Lonely man');
insert into movie_cast (actor_id, movie_id, roles) VALUES (9, 9, 'The father');
insert into movie_cast (actor_id, movie_id, roles) VALUES (10, 7, 'Little girl');