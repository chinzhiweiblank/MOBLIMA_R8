package Model;

/**
 * review class
 */
public class Review implements java.io.Serializable {
	private String name;
	private String movieName;
	private String time;
	private String review;
	private double rating;

	/**
	 * constructor for review class
	 */
	public Review() {

	};

	/**
	 * constructor for review class
	 * @param name name of moviegoer
	 * @param movieName name of movie
	 * @param rating rating of movie
	 * @param time time of movie
	 * @param review review of movie
	 */
	public Review(String name, String movieName, double rating, String time, String review) {

		this.name = name;
		this.movieName = movieName;
		this.rating = rating;
		this.time = time;
		this.review = review;
	}

	/**
	 * Obtains Reviewer's name
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Obtains title of movie reviewed
	 * @return String movie name
	 */
	public String getMovieName() {
		return this.movieName;
	}

	/**
	 * Obtains rating of movie reviewed
	 * @return Rating of movie reviewed
	 */
	public double getRating() {
		return this.rating;
	}
	/**
	 * Obtains time of creation
	 * @return Time of creation
	 */
	public String getTime() {
		return this.time;
	}

	/**
	 * Obtains Review Description
	 * @return Review Description
	 */
	public String getReview() {

		return this.review;
	}

	/**
	 * Sets the name of moviegoer
	 * @param name Name of moviegoer
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * Sets the rating
	 * @param rating Rating of the movie
	 */
	public void setRating(double rating) {

		this.rating = rating;
	}


	/**
	 * Sets the time of creation
	 * @param time Time of creation
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Sets review description
	 * @param review Review description
	 */
	public void setReview(String review) {

		this.review = review;
	}
}