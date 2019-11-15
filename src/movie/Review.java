package movie;

public class Review implements java.io.Serializable {
	private String name;
	private String movieName;
	private String time;
	private String review;
	private double rating;

	public Review() {
	};

	public Review(String name, String movieName, double rating, String time, String review) {
		this.name = name;
		this.movieName = movieName;
		this.rating = rating;
		this.time = time;
		this.review = review;
	}

	public String getName() {
		return this.name;
	}

	public String getMovieName() {
		return this.movieName;
	}

	public double getRating() {
		return this.rating;
	}

	public String getTime() {
		return this.time;
	}

	public String getReview() {
		return this.review;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setReview(String review) {
		this.review = review;
	}
}