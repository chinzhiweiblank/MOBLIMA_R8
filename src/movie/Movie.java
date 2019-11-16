package movie;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * movie listing class
 */
public class Movie implements java.io.Serializable {
	private String MovieTitle;
	private String Synopsis;
	private String Director;
	private ArrayList<String> Cast;
	private ShowingStatus ShowingStatus;
	private double OverallRating;
	private ArrayList<Review> pastReviews;
	private MovieType MovieType;

	public enum MovieType {
		IMAX, ThreeD, Blockbuster
	}

	/**
	 * constructor for movie
	 * @param MovieTitle title of the movie
	 * @param showingStatus showing status of the movie
	 */
	public Movie(String MovieTitle, ShowingStatus showingStatus) {

		this.MovieTitle = MovieTitle;
		this.ShowingStatus = showingStatus;
		this.Synopsis = "";
		this.Director = "";
		this.Cast = new ArrayList<String>();
		this.OverallRating = -1;
		this.pastReviews = new ArrayList<Review>();
	}

	/**
	 * another constructor for movie
	 * @param MovieTitle title of the movie
	 * @param showingStatus showing status of the movie
	 * @param Synopsis synopsis of the movie
	 * @param Director director of the movie
	 * @param Cast cast of the movie
	 * @param OverallRating overall rating of the movie
	 * @param pastReviews past reviews of the movie
	 * @param movieType type of the movie
	 */
	public Movie(String MovieTitle, ShowingStatus showingStatus, String Synopsis, String Director,
			ArrayList<String> Cast, double OverallRating, ArrayList<Review> pastReviews, MovieType movieType) {

		this.MovieTitle = MovieTitle;
		this.ShowingStatus = showingStatus;
		this.Synopsis = Synopsis;
		this.Director = Director;
		this.Cast = Cast;
		this.OverallRating = OverallRating;
		this.pastReviews = pastReviews;
		this.MovieType = movieType;
	}

	// getter methods
	/**
	 * get the movie title
	 * @return String MovieTitle
	 */
	public String getMovieTitle() {

		return MovieTitle;
	}

	/**
	 * get the showing status
	 * @return ShowingStatus showing status
	 */
	public ShowingStatus getShowingStatus() {

		return this.ShowingStatus;
	}

	/**
	 * get the synopsis
	 * @return String Synopsis
	 */
	public String getSynopsis() {

		return Synopsis;
	}

	/**
	 * get the director
	 * @return String Director
	 */
	public String getDirector() {

		return Director;
	}

	/**
	 * get the cast
	 * @return ArrayList<String> Cast
	 */
	public ArrayList<String> getCast() {

		return Cast;
	}

	/**
	 * get the overall rating
	 * @return double OverallRating
	 */
	public double getOverallRating() {

		return OverallRating;
	}

	/**
	 * get review
	 * @return ArrayList<Review> pastReviews
	 */
	public ArrayList<Review> getReview() {

		return pastReviews;
	}

	/**
	 * get type of movie
	 * @return MovieType Movie type
	 */
	public MovieType getMovieType() {

		return this.MovieType;
	}

	// setter methods
	/**
	 * set the movie title
	 * @param MovieTitle title of the movie
	 */
	public void setMovieTitle(String MovieTitle) {

		this.MovieTitle = MovieTitle;
	}

	/**
	 * set the showing status
	 * @param showingStatus showing status of the movie
	 */
	public void setShowingStatus(String showingStatus) {

		this.ShowingStatus = ShowingStatus.valueOf(showingStatus);
	}

	/**
	 * set the movie type
	 * @param movieType type of the movie
	 */
	public void setMovieType(String movieType) {

		this.MovieType = MovieType.valueOf(movieType);
	}

	/**
	 * set the synopsis
	 * @param Synopsis synopsis of the movie
	 */
	public void setSynopsis(String Synopsis) {

		this.Synopsis = Synopsis;
	}

	/**
	 * set the director
	 * @param Director director of the movie
	 */
	public void setDirector(String Director) {

		this.Director = Director;
	}

	/**
	 * set the cast
	 * @param Cast cast of the movie
	 */
	public void setCast(ArrayList<String> Cast) {

		this.Cast = Cast;
	}

	/**
	 * calculate the overall rating of the movie
	 */
	public void calculateOverallRating() {

		if (this.pastReviews.size() < 2) {
			this.OverallRating = -1;
			return;
		}

		double total = 0;
		for (Review review : this.pastReviews) {
			total += review.getRating();
		}

		// round to 1dp
		double Rating = total / this.pastReviews.size();
		this.OverallRating = Rating;
	}
	/**
	 * set the array list of review
	 * @param pastReviews past reviews of the movie
	 */
	public void setReview(ArrayList<Review> pastReviews) {

		this.pastReviews = pastReviews;
	}

	/**
	 * insert the individual review
	 * @param review of the movie
	 */
	public void insertReview(Review review) {

		this.pastReviews.add(review);
	}

	// other methods
	/**
	 * dumps the movie details into the console log
	 */
	public void dumpDetails() {

		// dumps the movie details into the console log
		System.out.printf("Movie title:%s\n", this.getMovieTitle());
		System.out.printf("Showing status:%s\n", this.getShowingStatus());
		System.out.printf("Synopsis:%s\n", this.getSynopsis());
		System.out.printf("Director:%s\n", this.getDirector());

		System.out.printf("Cast:\n");
		for (String cast : this.getCast()) {
			System.out.printf("%s ", cast);
		}
		System.out.printf("\n");

		if (this.OverallRating == -1) {
			System.out.printf("Overall review rating:%s\n", "NA");

		} else {
			DecimalFormat df = new DecimalFormat("#.#");
			System.out.printf("Overall review rating:%s\n", df.format(this.OverallRating));
		}

		System.out.printf("Past Reviews:\n");
		for (Review review : this.getReview()) {
			System.out.printf("%s\n", review.getReview());
		}
	}
}