package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class movieBean {

	private int _id;
	private String _movie_title;
	private int _release_year;
	private int _runtime;

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getMovie_title() {
		return _movie_title;
	}

	public void setMovie_title(String movie_title) {
		this._movie_title = movie_title;
	}

	public int getRelease_year() {
		return _release_year;
	}

	public void setRelease_year(int release_year) {
		this._release_year = release_year;
	}

	public int getRuntime() {
		return _runtime;
	}

	public void setRuntime(int runtime) {
		this._runtime = runtime;
	}

	public String toString() {
		String pattern = "Movie Title = %s, Release Year = %d, Movie Runtime = %d";
		String text = String.format(pattern, this._movie_title, this._release_year, this._runtime);

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Movie Title", this._movie_title));
		datalist.add(new keyvaluepair("Release Year", Integer.toString(this._release_year)));
		datalist.add(new keyvaluepair("Movie Runtime", Integer.toString(this._runtime)));

		return jsonHelper.toJsonObject(datalist);
	}

}
