package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import beans.movieBean;
import helpers.jsonHelper;

public class movies {

	private Connection _connection;
	private ArrayList<movieBean> _movies;

	private String createMovie = "INSERT INTO movies (movie_titel, release_year, runtime) VALUE (?, ?, ?)";
	private String readAllMovies = "SELECT * FROM movies";
	private String updateMovieLength = "UPDATE movies SET runtime=? WHERE movie_titel=?";
	private String deleteMovie = "DELETE FROM movies WHERE movie_titel=?";

	public movies(Connection cn) {
		this._connection = cn;
		this._movies = new ArrayList<movieBean>();
		getMovies();

	}

	public int createMovie(String movie_title, int release_year, int runtime) {
		int result = -1;

		if (movie_title.isBlank() || release_year < 1200 || runtime == 1)
			return -1;

		try (CallableStatement cst = (CallableStatement) this._connection.prepareCall(createMovie)) {
			cst.setString(1, movie_title);
			cst.setInt(2, release_year);
			cst.setInt(3, runtime);
			result = cst.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());

		} catch (SQLException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<movieBean> getMovies() {
		if (this._movies.size() > 0)
			return this._movies;

		try (PreparedStatement myQry = this._connection.prepareStatement(readAllMovies)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		return this._movies;
	}

	public int updateMovieLength(String movie_title, int newRunTime) {
		int count = -1;

		if (movie_title.isBlank() || newRunTime == 0)
			return -1;

		try (PreparedStatement myQry = this._connection.prepareStatement(updateMovieLength)) {
			myQry.setInt(1, newRunTime);
			myQry.setString(2, movie_title);
			count = myQry.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}

		return count;
	}

	public int deleteMovie(String movie_title) {

		int count = -1;
		try (PreparedStatement myQry = this._connection.prepareStatement(deleteMovie)) {
			myQry.setString(1, movie_title);
			count = myQry.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}

		return count;
	}

	public String toJson() {
		String beansContent = "";
		for (movieBean ab : this._movies) {
			beansContent += ab.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Movies", beansContent);
	}

	private movieBean buildMovie(ResultSet rs) {
		movieBean mb = new movieBean();

		try {
			mb.setId(rs.getInt("movie_id"));
			mb.setMovie_title(rs.getString("movie_titel"));
			mb.setRelease_year(rs.getInt("release_year"));
			mb.setRuntime(rs.getInt("runtime"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mb;
	}

	private void buildMovies(ResultSet rs) throws SQLException {
		while (rs.next()) { // rows
			this._movies.add(buildMovie(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMovies(rs);
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

	}

}
