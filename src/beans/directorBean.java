package beans;

public class directorBean {

	private int _id;
	private String _dir_first_name;
	private String _dir_last_name;

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getDir_first_name() {
		return _dir_first_name;
	}

	public void setDir_first_name(String dir_first_name) {
		this._dir_first_name = dir_first_name;
	}

	public String getDir_last_name() {
		return _dir_last_name;
	}

	public void setDir_last_name(String dir_last_name) {
		this._dir_last_name = dir_last_name;
	}

	public String toString() {
		String pattern = "First Namn = %s, Last Name = %s";
		String returnString = String.format(pattern, this._dir_first_name, this._dir_last_name);

		return returnString;
	}

	public String toJson() {
		String pattern = "{ \"First Name\": \"%s\", \"Last Name\": \"%s\"}";
		String returnString = String.format(pattern, this._dir_first_name, this._dir_last_name);

		return returnString;
	}

}
