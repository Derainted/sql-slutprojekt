package beans;

public class actorToCastBean {

	private int _actor_id;
	private int _movie_id;
	private String _roles;

	public int getActor_id() {
		return _actor_id;
	}

	public void setActor_id(int actor_id) {
		this._actor_id = actor_id;
	}

	public int getMovie_id() {
		return _movie_id;
	}

	public void setMovie_id(int movie_id) {
		this._movie_id = movie_id;
	}

	public String getRoles() {
		return _roles;
	}

	public void setRoles(String roles) {
		this._roles = roles;
	}

	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}

}
