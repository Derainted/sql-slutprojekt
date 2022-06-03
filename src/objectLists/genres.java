package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.genreBean;
import helpers.jsonHelper;

public class genres {
	
	private Connection _connection;
	private ArrayList<genreBean> _genres;
	
	private String createGenre = "INSERT INTO genres (gen_titel) VALUES (?)";
	private String readAllGenre = "SELECT * FROM genres";
	private String updateGenre = "UPDATE genres SET gen_titel=? WHERE gen_titel=?";
	private String deleteGenre = "DELETE FROM genres WHERE gen_titel=?";
	
	public genres(Connection cn) {
		this._connection = cn;
		this._genres = new ArrayList<genreBean>();
		get_genres();
	}

	public int createGenre(String gen_title) {
		int count = -1;
		
		if (gen_title.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(createGenre)) {
			myQry.setString(1, gen_title);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public ArrayList<genreBean> get_genres() {
		
		try (PreparedStatement myQry = this._connection.prepareStatement(readAllGenre)) { 
			try (ResultSet rs = myQry.executeQuery()) {
				
				while(rs.next()) {
					genreBean gb = new genreBean();
					gb.setId(rs.getInt("gen_id"));
					gb.setGen_title(rs.getString("gen_titel"));
					
					this._genres.add(gb);
				}
				
			} catch (SQLException e) {
				System.out.println("Something went wrong");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
		return _genres;
	}
	
	public int updateGenre(String gen_title, String newGen_title) {
		int count = -1;
		
		if (gen_title.isBlank() || newGen_title.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(updateGenre)) {
			myQry.setString(1, newGen_title);
			myQry.setString(2, gen_title);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
		return count;
	}

	public int deleteGenre(String gen_title) {
		int count = -1;
		
		if (gen_title.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(deleteGenre)) {
			myQry.setString(1, gen_title);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
		return count;
	}

	public String toJson() {
		String beansContent = "";
		for (genreBean gb : this._genres) {
			beansContent += gb.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Genres", beansContent);
	}

}
