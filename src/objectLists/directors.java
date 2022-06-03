package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.directorBean;
import helpers.jsonHelper;

public class directors {
	
	private Connection _connection;
	private ArrayList<directorBean> _directors;
	
	private String createDirector = "INSERT INTO directors (dir_first_name , dir_last_name) "
			+ "VALUES (?,?) ON DUPLICATE KEY UPDATE dir_id = dir_id";
	private String readAllDirectors = "SELECT * FROM directors";
	private String updateDirectorLastName = "UPDATE directors SET dir_last_name=? WHERE dir_first_name=?";
	private String deleteDirector = "DELETE FROM directors WHERE dir_first_name=?";

	public directors(Connection cn) {
		this._connection = cn;
		this._directors = new ArrayList<directorBean>();
		getDirectors();
	}

	public int createDirector(String dir_first_name, String dir_last_name) {
		int count = -1;
		
		if (dir_first_name.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(createDirector)) {
			myQry.setString(1, dir_first_name);
			myQry.setString(2, dir_last_name);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public ArrayList<directorBean> getDirectors() {
		if (this._directors.size() > 0) 
			return this._directors;
			
		
		try {
			PreparedStatement myQry = this._connection.prepareStatement(readAllDirectors);
			ResultSet rs = myQry.executeQuery();
			while(rs.next()) {  
				this._directors.add(buildAddress(rs));
			}
			
			rs.close();
			myQry.close();			
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		return this._directors;
	}

	public int updateDirector(String dir_first_name, String newDir_last_name) {
		int count = -1;
		
		if (dir_first_name.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(updateDirectorLastName)) {
			myQry.setString(1, dir_first_name);
			myQry.setString(2, newDir_last_name);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
		return count;
	}

	public int deleteDirector(String dir_first_name) {
		int count = -1;
		
		if (dir_first_name.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(deleteDirector)) {
			myQry.setString(1, dir_first_name);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
		return count;
	}

	public String toJson() {
		String beansContent = "";
		for (directorBean db : this._directors) {
			beansContent += db.toJson() + ",";
		}

		return jsonHelper
				.toJsonArray("Directors", beansContent);
	}
	
 	private directorBean buildAddress(ResultSet rs) {
 		directorBean ab = new directorBean();

		try {
			ab.setId(rs.getInt("dir_id"));
			ab.setDir_first_name(rs.getString("dir_first_name"));
			ab.setDir_last_name(rs.getString("dir_last_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ab;
	}


}
