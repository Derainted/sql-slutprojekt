package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import beans.movieGenreBean;
import helpers.jsonHelper;

public class movieGenre {

	private Connection _connection;
	private ArrayList<movieGenreBean> _genreToMovie;

	private String createMovieGenres = "INSERT INTO movie_genres (movie_id, gen_id) (?,?)";
	private String readRelations = "SELECT * FROM movie_genres";

	public movieGenre(Connection cn) {
		this._connection = cn;
		this._genreToMovie = new ArrayList<movieGenreBean>();
		getGenreToMovie();
	}

	public ArrayList<movieGenreBean> getGenreToMovie() {

		if (this._genreToMovie.size() > 0)
			return this._genreToMovie;

		try (PreparedStatement myQry = this._connection.prepareStatement(readRelations)) {
			runQuery(myQry);

		} catch (SQLException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}

		return this._genreToMovie;
	}
	
	public int createMovieGenres(int movie_id, int gen_id) {
		int result = -1;
		
		if (movie_id == 0 || gen_id == 0) return -1;
		
		try (CallableStatement cst = (CallableStatement) this._connection.prepareCall(createMovieGenres)) {
			cst.setInt(1, movie_id);
			cst.setInt(2, gen_id);
			result = cst.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("getActor exception for statement");
			e.printStackTrace();
		}
		
		return result;
	}

	

	public String toJson() {
		String beansContent = "";
		for (movieGenreBean mg : this._genreToMovie) {
			beansContent += mg.toJson() + ",";
		}

		return jsonHelper.toJsonArray("MovieGenreRelations", beansContent);
	}

	private movieGenreBean buildMoviegenre(ResultSet rs) {
		movieGenreBean mg = new movieGenreBean();

		try {
			mg.setGen_id(rs.getInt("gen_id"));
			mg.setMovie_id(rs.getInt("movie_id"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mg;
	}

	private void buildMoviegenres(ResultSet rs) throws SQLException {
		while (rs.next()) { // rows
			this._genreToMovie.add(buildMoviegenre(rs));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMoviegenres(rs);
		} catch (SQLException e) {
			System.out.println("getMoviegenre_relations exception for result set");
			e.printStackTrace();
		}

	}

}
