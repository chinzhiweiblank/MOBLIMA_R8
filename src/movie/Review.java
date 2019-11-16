package movie;

/**
 * review class
 */
public class Review implements java.io.Serializable {
	private String name;
	private String movieName;
	private String time;
	private String review;
	private double rating;

	public Review() {
		/**
		 * constructor for review class
		 */
	};

	public Review(String name, String movieName, double rating, String time, String review) {
		/**
		 * constructor for review class
		 * @param String name
		 * @param String movieName
		 * @param double rating
		 * @param String time
		 * @param review
		 */
		this.name = name;
		this.movieName = movieName;
		this.rating = rating;
		this.time = time;
		this.review = review;
	}

	public String getName() {
		/**
		 * get name
		 * @return String name
		 */
		return this.name;
	}

	public String getMovieName() {
		/**
		 * get movie name
		 * @return String movie name
		 */
		return this.movieName;
	}

	public double getRating() {
		/**
		 * get movie rating
		 * @return double rating
		 */
		return this.rating;
	}

	public String getTime() {
		/**
		 * get time
		 * @return String time
		 */
		return this.time;
	}

	public String getReview() {
		/**
		 * get review
		 * @return String review
		 */
		return this.review;
	}

	public void setName(String name) {
		/**
		 * set the name
		 * @param String name
		 */
		this.name = name;
	}

	public void setRating(double rating) {
		/**
		 * set the rating
		 * @param double rating
		 */
		this.rating = rating;
	}

	public void setTime(String time) {
		/**
		 * set the time
		 * @param String time
		 */
		this.time = time;
	}

	public void setReview(String review) {
		/**
		 * set the review
		 * @param String review
		 */
		this.review = review;
	}
}