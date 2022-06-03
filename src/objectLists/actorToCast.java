package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import beans.actorToCastBean;
import helpers.jsonHelper;

public class actorToCast {
	
	private Connection _connection;
	private ArrayList<actorToCastBean> _movieActorToCast;
	
	private String createActorToCast = "INSERT INTO movie_cast (actor_id, movie_id, roles)";
	private String readRelations = "SELECT * FROM movie_cast";
	
	public actorToCast(Connection cn) {
		this._connection = cn;
		this._movieActorToCast = new ArrayList<actorToCastBean>();
		getMovieActorToCast();
	}
	
	public ArrayList<actorToCastBean> getMovieActorToCast(){
		
		if (this._movieActorToCast.size() > 0)
			return this._movieActorToCast;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(readRelations)) {
			runQuery(myQry);
			
		} 
		
		
		
		catch (SQLException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
		
		return this._movieActorToCast;
	}
	
	public int createActorToCast(int actor_id, int movie_id, String roles) {
		int result = -1;
		
		if (actor_id == 0 || movie_id == 0  || roles.isBlank()) return -1;
		
		try (CallableStatement cst = (CallableStatement) this._connection.prepareCall(createActorToCast)) {
			cst.setInt(1, actor_id);
			cst.setInt(2, movie_id);
			cst.setString(3, roles);
			result = cst.executeUpdate();
			
			
		}catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println("createActorToMovieRelation exception for statement");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String toJson() {
		String beansContent = "";
		for (actorToCastBean ma : this._movieActorToCast) {
			beansContent += ma.toJson() + ",";
		}

		return jsonHelper
				.toJsonArray("MovieActorRelations", beansContent);
	}
	
	private actorToCastBean buildMovieactor(ResultSet rs) {
		actorToCastBean ma = new actorToCastBean();

		try {
			ma.setActor_id(rs.getInt("actor_id"));
			ma.setMovie_id(rs.getInt("movie_id"));
			ma.setRoles(rs.getString("roles"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ma;
	}
	
 	private void buildMovieactors(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._movieActorToCast.add(buildMovieactor(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMovieactors(rs);
		} catch (SQLException e) {
			System.out.println("getMovieActorRelations exception for result set");
			e.printStackTrace();
		}

 	}

}
