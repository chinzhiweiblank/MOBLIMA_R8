package commons;

public class Cineplex {
	private String location;
	private String Name;
	private int numCinemas;

	public Cineplex(String location, String Name, int numCinemas) {
		this.location = location;
		this.Name = Name;
		this.numCinemas = numCinemas;
	}

	public String getLocation() {
		return this.location;
	}

	public int getNumCinemas() {
		return this.numCinemas;
	}

	public String getName() {
		return this.Name;
	}
}
