package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.actorBean;
import helpers.jsonHelper;

public class actors {

	private Connection _connection;
	private ArrayList<actorBean> _actors;

	private String createActor = "INSERT INTO actors (actor_first_name, actor_last_name)"
			+ "VALUES (?,?) ON DUPLICATE KEY UPDATE actor_id = actor_id";
	private String readAllActors = "SELECT * FROM actors";
	private String updateActor = "UPDATE actors SET actor_last_name=? WHERE actor_first_name=?";
	private String updateActorLastName = "UPDATE actors SET actor_first_name=? WHERE actor_last_name=?";
	private String deleteActors = "DELETE FROM actors WHERE actor_last_name=?";

	public actors(Connection cn) {
		this._connection = cn;
		this._actors = new ArrayList<actorBean>();
		getActors();

	}

	public ArrayList<actorBean> getActors() {
		if (this._actors.size() > 0)
			return this._actors;

		try (PreparedStatement myQry = this._connection.prepareStatement(readAllActors)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		return this._actors;
	}

	public int createActor(String actor_first_name, String actor_last_name ) {
		int count = -1;

		if (actor_first_name.isBlank()) return -1;


		try (PreparedStatement myQry = this._connection.prepareStatement(createActor)) {
			myQry.setString(1, actor_first_name);
			myQry.setString(2, actor_last_name);
			count = myQry.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		return count;
	}

	public int updateActor(String actor_first_name, String newActor_last_name, String function) {
		int count = -1;

		

		try (PreparedStatement myQry = this._connection.prepareStatement(selectQueryString(function))) {

			count = selectQuery(myQry, actor_first_name, newActor_last_name,  function);

		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		return count;
	}

	public int deleteActor(String actor_first_name) {

		int count = -1;
		try (PreparedStatement myQry = this._connection.prepareStatement(deleteActors)) {
			myQry.setString(1, actor_first_name);
			count = myQry.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		return count;
	}

	public String toJson() {
		String beansContent = "";
		for (actorBean ab : this._actors) {
			beansContent += ab.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Actors", beansContent);
	}

	private actorBean buildActor(ResultSet rs) {
		actorBean ab = new actorBean();

		try {
			ab.setId(rs.getInt("actor_id"));
			ab.setActor_first_name(rs.getString("actor_first_name"));
			ab.setActor_last_name(rs.getString("actor_last_name"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ab;
	}

	private void buildActors(ResultSet rs) throws SQLException {
		while (rs.next()) { // rows
			this._actors.add(buildActor(rs));
		}
	}

	private void runQuery(PreparedStatement query) {

		try (ResultSet rs = query.executeQuery()) {
			buildActors(rs);
		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

	}

	private String selectQueryString(String function) {
		String returnString = null;


		if (function.equals("skills"))
			returnString = updateActorLastName;
		else if (function.equals("all"))
			returnString = updateActor;

		return returnString;

	}

	private int selectQuery(PreparedStatement myQry, String actor_first_name, String newActor_last_name, 
			String function)
			throws SQLException {
		int count = -1;

	
		if (function.equals("actor_last_name")) {
			myQry.setString(1, newActor_last_name);
			myQry.setString(2, actor_first_name);
			count = myQry.executeUpdate();

		} else if (function.equals("all")) {
			myQry.setString(1, newActor_last_name);
			myQry.setString(2, actor_first_name);
			count = myQry.executeUpdate();
		}

		return count;

	}

}
