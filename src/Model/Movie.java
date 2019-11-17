package Model;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Model Class for Movie
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
	 * Constructor for movie
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
	 * Constructor for movie
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
	 * Obtains the movie title
	 * @return Title of movie
	 */
	public String getMovieTitle() {

		return MovieTitle;
	}

	/**
	 * Obtains the showing status
	 * @return Showing status
	 */
	public ShowingStatus getShowingStatus() {

		return this.ShowingStatus;
	}

	/**
	 * Obtains the synopsis
	 * @return Movie synopsis
	 */
	public String getSynopsis() {

		return Synopsis;
	}

	/**
	 * Obtains the name of the director
	 * @return Director's name
	 */
	public String getDirector() {

		return Director;
	}

	/**
	 * Obtains the cast
	 * @return A list of cast members
	 */
	public ArrayList<String> getCast() {

		return Cast;
	}

	/**
	 * Obtains the overall rating
	 * @return Overall Rating of the movie
	 */
	public double getOverallRating() {

		return OverallRating;
	}

	/**
	 * Obtians review
	 * @return A list of past reviews
	 */
	public ArrayList<Review> getReview() {

		return pastReviews;
	}

	/**
	 * Obtains type of movie
	 * @return Type of movie
	 */
	public MovieType getMovieType() {

		return this.MovieType;
	}

	/**
	 * Sets the movie title
	 * @param MovieTitle Title of movie
	 */
	public void setMovieTitle(String MovieTitle) {

		this.MovieTitle = MovieTitle;
	}

	/**
	 * Sets the showing status
	 * @param showingStatus Showing Status of Movie
	 */
	public void setShowingStatus(String showingStatus) {

		this.ShowingStatus = ShowingStatus.valueOf(showingStatus);
	}

	/**
	 * Sets the movie type
	 * @param movieType Type of the movie
	 */
	public void setMovieType(String movieType) {

		this.MovieType = MovieType.valueOf(movieType);
	}

	/**
	 * Sets the synopsis
	 * @param Synopsis Synopsis of the movie
	 */
	public void setSynopsis(String Synopsis) {

		this.Synopsis = Synopsis;
	}

	/**
	 * Sets the director's name
	 * @param Director Name of movie director
	 */
	public void setDirector(String Director) {

		this.Director = Director;
	}

	/**
	 * Sets the cast
	 * @param Cast Cast of the movie
	 */
	public void setCast(ArrayList<String> Cast) {

		this.Cast = Cast;
	}

	/**
	 * Calculates the overall rating of the movie
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
	 * Sets the list of reviews
	 * @param pastReviews Past reviews of the movie
	 */
	public void setReview(ArrayList<Review> pastReviews) {

		this.pastReviews = pastReviews;
	}

	/**
	 * Inserts the individual review
	 * @param review New review of the movie
	 */
	public void insertReview(Review review) {

		this.pastReviews.add(review);
	}

	/**
	 * Prints Movie Details
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