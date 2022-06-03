package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class genreBean {

	private int _id;
	private String _gen_title;

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getGen_title() {
		return _gen_title;
	}

	public void setGen_title(String gen_title) {
		this._gen_title = gen_title;
	}

	public String toString() {
		String pattern = "Genre = %s";
		String text = String.format(pattern, this._gen_title);

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Genre", this._gen_title));

		return jsonHelper.toJsonObject(datalist);
	}

}
