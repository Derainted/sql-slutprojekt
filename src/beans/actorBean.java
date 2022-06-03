package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class actorBean {

	private int _id;
	private String _actor_first_name;
	private String _actor_last_name;

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getActor_first_name() {
		return _actor_first_name;
	}

	public void setActor_first_name(String actor_first_name) {
		this._actor_first_name = actor_first_name;
	}

	public String getActor_last_name() {
		return _actor_last_name;
	}

	public void setActor_last_name(String actor_last_name) {
		this._actor_last_name = actor_last_name;
	}

	public String toString() {
		String pattern = "First Namn = %s, Last Name = %s";
		String text = String.format(pattern, this._actor_first_name, this._actor_last_name);

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("First Name", this._actor_first_name));
		datalist.add(new keyvaluepair("Last Name", this._actor_last_name));

		return jsonHelper.toJsonObject(datalist);
	}

}
