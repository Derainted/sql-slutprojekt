package java_sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import helpers.databaseHelper;
import objectLists.actorToCast;
import objectLists.actors;
import objectLists.directors;
import objectLists.genres;
import objectLists.movies;

public class main_class {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Connection conn = databaseHelper.DbConnect("movies");

		userUI(conn);

		conn.close();

	}

	private static void userUI(Connection conn) {

		int sel = 666;
		try (Scanner scann = new Scanner(System.in)) {
			while (true) {
				System.out.println("----Main menu----");

				System.out.println("-----ACTORS-----");

				System.out.println(" 1. ADD NEW ACTOR ");
				System.out.println(" 2. LIST OF ALL ACTORS");
				System.out.println(" 3. UPDATE ACTORS NAME");
				System.out.println(" 4. DELETE AN ACTOR");

				System.out.println("-----DIRECTORS-----");

				System.out.println(" 5. ADD NEW DIRECTOR ");
				System.out.println(" 6. LIST OF ALL DIRECTORS");
				System.out.println(" 7. UPDATE DIRECTOR LAST NAME");
				System.out.println(" 8. DELETE A DIRECTOR");

				System.out.println("-----GENRES-----");

				System.out.println(" 9. ADD NEW GENRE ");
				System.out.println(" 10. LIST OF ALL GENRE");
				System.out.println(" 11. UPDATE A GENRE");
				System.out.println(" 12. DELETE GENRE");

				System.out.println("-----MOVIES------");

				System.out.println(" 13. ADD A NEW MOVIE");
				System.out.println(" 14. READ ALL MOVIE");
				System.out.println(" 15. UPDATE MOVIE RUNTIME");
				System.out.println(" 16. DELETE MOVIE");

				System.out.println("-----ACTOR TO CAST----_");

				System.out.println(" 17. CREATE ACTOR TO CAST");
				System.out.println(" 18. READ ACTOR TO CAST");

				try {
					sel = scann.nextInt();
				} catch (Exception e) {
					System.out.println("FEL SÖKNING");
					scann.next();
				}

				switch (sel) {

				// actors
				case 1 -> {
					System.out.println("ADD NEW ACTOR");
					System.out.println("actor_first_name: ");
					String actor_first_name = scann.next();
					System.out.println("actor_last_name: ");
					String actor_last_name = scann.next();

					createActor(conn, actor_first_name, actor_last_name);
				}
				case 2 -> {
					System.out.println("LIST OF ALL ACTORS");
					readActors(conn);
				}
				case 3 -> {
					System.out.println("-UPDATE ACTOR FULL NAME-");
					System.out.println("First Name: ");
					String actor_first_name = scann.next();
					System.out.println("Last Name: ");
					String actor_last_name = scann.next();

					updateActor(conn, actor_first_name, actor_last_name);
				}

				case 4 -> {
					System.out.println("DELETE AN ACTOR");
					System.out.println("First Name: ");
					String actor_first_name = scann.next();

					deleteActor(conn, actor_first_name);
				}

				// directors
				case 5 -> {
					System.out.println("-ADD A NEW DIRECTOR");
					System.out.println("First Name of director: ");
					String dir_first_name = scann.next();
					System.out.println("Last Name: ");
					String dir_last_name = scann.next();

					createDirector(conn, dir_first_name, dir_last_name);
				}
				case 6 -> {
					System.out.println("LIST OF ALL DIRECTORS");

					readDirectors(conn);
				}
				case 7 -> {
					System.out.println("UPDATE DIRECTOR LAST NAME");
					System.out.println("First Name of Director: ");
					String dir_first_name = scann.next();
					System.out.println("Director's new Last Name: ");
					String dir_last_name = scann.next();

					updateDirectorName(conn, dir_first_name, dir_last_name);
				}
				case 8 -> {
					System.out.println("DELETE DIRECTOR");
					System.out.println("Name of director: ");
					String dir_first_name = scann.next();

					deleteDirector(conn, dir_first_name);
				}

				// genres
				case 9 -> {
					System.out.println("ADD NEW GENRE");
					System.out.println("Name of genre: ");
					String gen_title = scann.next();

					createGenre(conn, gen_title);
				}
				case 10 -> {
					System.out.println("LIST OF ALL GENRE");

					readGenres(conn);
				}
				case 11 -> {
					System.out.println("UPDATE A GENRE");
					System.out.println("Name of genre: ");
					String gen_title = scann.next();
					System.out.println("New genre: ");
					String newGenre_title = scann.next();

					updateGenre(conn, gen_title, newGenre_title);
				}
				case 12 -> {
					System.out.println("DELETE GENRE");
					System.out.println("Name of genre: ");
					String gen_title = scann.next();

					deleteGenre(conn, gen_title);
				}

				// movies
				case 13 -> {
					System.out.println("CREATE A MOVIE");
					System.out.println("Movie title: ");
					String movie_title = scann.next();
					System.out.println("Release year: ");
					int release_year = scann.nextInt();
					System.out.println("Length minutes: ");
					int runtime = scann.nextInt();

					createMovie(conn, movie_title, release_year, runtime);
				}
				case 14 -> {
					System.out.println("READ ALL MOVIES");

					readMovies(conn);
				}
				case 15 -> {
					System.out.println("UPDATE MOVIE RUNTIME");
					System.out.println("Movie title: ");
					String movie_title = scann.next();
					System.out.println("New runtime: ");
					int runtime = scann.nextInt();

					updateMovieLength(conn, movie_title, runtime);
				}
				case 16 -> {
					System.out.println("DELETE MOVIE");
					System.out.println("Movie title: ");
					String movie_title = scann.next();

					deleteMovie(conn, movie_title);
				}

				case 17 -> {
					System.out.println("CREATE ACTOR TO CAST");
					System.out.println("Actor name: ");
					int actor_id = scann.nextInt();
					System.out.println("Movie title: ");
					int movie_id = scann.nextInt();
					System.out.println("Actor Role");
					String roles = scann.next();

					createActorToCast(conn, actor_id, movie_id, roles);
				}
				case 18 -> {
					System.out.println("READ CASTING ACTOR");

					readActorToMovieRelations(conn);
				}

				default -> System.out.println("0");
				}
			}
		}

	}

	private static void createActor(Connection conn, String actor_first_name, String actor_last_name) {
		actors myActor = new actors(conn);

		int nrCreated = myActor.createActor(actor_first_name, actor_last_name);
		System.out.println("Nr of created actors : " + nrCreated);

	}

	private static void readActors(Connection conn) {
		actors myActors = new actors(conn);
		String jsonDoc = "{" + myActors.toJson() + "}";

		System.out.println(jsonDoc);

	}

	private static void updateActor(Connection conn, String actor_first_name, String newActor_last_name) {
		actors myActors = new actors(conn);

		int nrUpdates = myActors.updateActor(actor_first_name, newActor_last_name, "all");
		System.out.println("Nr of updated actors : " + nrUpdates);

	}

	private static void deleteActor(Connection conn, String actor_last_name) {
		actors myActor = new actors(conn);

		int nrDeleted = myActor.deleteActor(actor_last_name);
		System.out.println("Nr of deleted actors : " + nrDeleted);

	}

	private static void createDirector(Connection conn, String dir_first_name, String dir_last_name) {
		directors myDirector = new directors(conn);

		int nrCreated = myDirector.createDirector(dir_first_name, dir_last_name);
		System.out.println("Nr of created directors : " + nrCreated);

	}

	private static void readDirectors(Connection conn) {
		directors myDirectors = new directors(conn);
		String jsonDoc = "{" + myDirectors.toJson() + "}";

		System.out.println(jsonDoc);

	}

	private static void updateDirectorName(Connection conn, String dir_first_name, String newDir_last_name) {
		directors myDirector = new directors(conn);

		int nrUpdates = myDirector.updateDirector(dir_first_name, newDir_last_name);
		System.out.println("Nr of updated directors : " + nrUpdates);

	}

	private static void deleteDirector(Connection conn, String dir_first_name) {
		directors myDirector = new directors(conn);

		int nrDeleted = myDirector.deleteDirector(dir_first_name);
		System.out.println("Nr of deleted directors : " + nrDeleted);

	}

	private static void createGenre(Connection conn, String gen_title) {
		genres myGenre = new genres(conn);

		int nrCreated = myGenre.createGenre(gen_title);
		System.out.println("Nr of created genres : " + nrCreated);

	}

	private static void readGenres(Connection conn) {
		genres myGenres = new genres(conn);
		String jsonDoc = "{" + myGenres.toJson() + "}";

		System.out.println(jsonDoc);

	}

	private static void updateGenre(Connection conn, String gen_title, String newGen_title) {
		genres myGenre = new genres(conn);

		int nrUpdates = myGenre.updateGenre(gen_title, newGen_title);
		System.out.println("Nr of updated genres : " + nrUpdates);

	}

	private static void deleteGenre(Connection conn, String gen_title) {
		genres myGenre = new genres(conn);

		int nrDeleted = myGenre.deleteGenre(gen_title);
		System.out.println("Nr of deleted genres : " + nrDeleted);

	}

	private static void createMovie(Connection conn, String movie_title, int release_year, int runtime) {

		movies myMovie = new movies(conn);
		System.out.println(myMovie.createMovie(movie_title, release_year, runtime));

	}

	private static void readMovies(Connection conn) {
		movies myMovies = new movies(conn);
		String jsonDoc = "{" + myMovies.toJson() + "}";

		System.out.println(jsonDoc);

	}

	private static void updateMovieLength(Connection conn, String movie_title, int runtime) {
		movies myMovie = new movies(conn);

		int nrUpdates = myMovie.updateMovieLength(movie_title, runtime);
		System.out.println("Nr of updated movies : " + nrUpdates);
	}

	private static void deleteMovie(Connection conn, String movie_title) {
		movies myMovie = new movies(conn);

		int nrDeleted = myMovie.deleteMovie(movie_title);
		System.out.println("Nr of deleted movies : " + nrDeleted);

	}

	private static void createActorToCast(Connection conn, int actor_id, int movie_id, String roles) {
		actorToCast ma_rel = new actorToCast(conn);

		int nrCreated = ma_rel.createActorToCast(actor_id, movie_id, roles);

		System.out.println("Nr of created relations : " + nrCreated);

	}

	private static void readActorToMovieRelations(Connection conn) {
		actorToCast ma_rel = new actorToCast(conn);
		String jsonDoc = "{" + ma_rel.toJson() + "}";

		System.out.println(jsonDoc);

	}

}
