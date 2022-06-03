package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class movieGenreBean {

	private int _movie_id;
	private int _gen_id;

	public int getMovie_id() {
		return _movie_id;
	}

	public void setMovie_id(int movie_id) {
		this._movie_id = movie_id;
	}

	public int getGen_id() {
		return _gen_id;
	}

	public void setGen_id(int gen_id) {
		this._gen_id = gen_id;
	}

	public String toString() {
		String pattern = "MovieId = %d, GenreId = %d";
		String text = String.format(pattern, this._movie_id, this._gen_id);

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair(Integer.toString(this._movie_id), 
									  Integer.toString(this._gen_id)));

		return jsonHelper.toJsonObject(datalist);
	}
}



