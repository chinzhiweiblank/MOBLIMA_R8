package commons;

/**
 * Model Class representing a cineplex
 */
public class Cineplex {
	/**
	 * Location of the cineplex
	 */
	private String location;
	/**
	 * Name of the cineplex
	 */
	private String Name;
	/**
	 * Number of cinemas in the cineplex
	 */
	private int numCinemas;

	/**
	 * Constructor of the cineplex object
	 * @param location the location of the cineplex
	 * @param Name the name of the cineplex
	 * @param numCinemas the number of cinemas in the cineplex
	 */
	public Cineplex(String location, String Name, int numCinemas) {
		this.location = location;
		this.Name = Name;
		this.numCinemas = numCinemas;
	}

	/**
	 * Obtains the location of the cineplex
	 * @return the location of the cineplex
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Obtains the number of cinemas in the cineplex
	 * @return the number of cinemas in the cineplex
	 */
	public int getNumCinemas() {
		return this.numCinemas;
	}

	/**
	 * Obtains the name of the cineplex
	 * @return the name of the cineplex
	 */
	public String getName() {
		return this.Name;
	}
}
