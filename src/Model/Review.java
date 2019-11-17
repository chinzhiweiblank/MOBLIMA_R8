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
	 * get name
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get movie name
	 * @return String movie name
	 */
	public String getMovieName() {
		return this.movieName;
	}

	/**
	 * get movie rating
	 * @return double rating
	 */
	public double getRating() {
		return this.rating;
	}
	/**
	 * get time
	 * @return String time
	 */
	public String getTime() {
		return this.time;
	}

	/**
	 * get review
	 * @return String review
	 */
	public String getReview() {

		return this.review;
	}

	/**
	 * set the name
	 * @param name name of moviegoer
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * set the rating
	 * @param rating rating of the movie
	 */
	public void setRating(double rating) {

		this.rating = rating;
	}


	/**
	 * set the time
	 * @param time time of the movie
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * set the review
	 * @param review review of the movie
	 */
	public void setReview(String review) {

		this.review = review;
	}
}