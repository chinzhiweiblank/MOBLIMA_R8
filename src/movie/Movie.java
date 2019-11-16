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

	public Movie(String MovieTitle, ShowingStatus showingStatus) {
		/**
		 * constructor for movie
		 * @param String MovieTitle
		 * @param ShowingStatus showingStatus
		 */
		this.MovieTitle = MovieTitle;
		this.ShowingStatus = showingStatus;
		this.Synopsis = "";
		this.Director = "";
		this.Cast = new ArrayList<String>();
		this.OverallRating = -1;
		this.pastReviews = new ArrayList<Review>();
	}

	public Movie(String MovieTitle, ShowingStatus showingStatus, String Synopsis, String Director,
			ArrayList<String> Cast, double OverallRating, ArrayList<Review> pastReviews, MovieType movieType) {
		/**
		 * another constructor for movie
		 * @param String MovieTitle
		 * @param ShowingStatus showingStatus
		 * @param String Synopsis
		 * @param String Director
		 * @param ArrayList<String> Cast
		 * @param double OverallRating
		 * @param ArrayList<Review> pastReviews
		 * @param MovieType movieType
		 */
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
	public String getMovieTitle() {
		/**
		 * get the movie title
		 * @return String MovieTitle
		 */
		return MovieTitle;
	}

	public ShowingStatus getShowingStatus() {
		/**
		 * get the showing status
		 * @return ShowingStatus showing status
		 */
		return this.ShowingStatus;
	}

	public String getSynopsis() {
		/**
		 * get the synopsis
		 * @return String Synopsis
		 */
		return Synopsis;
	}

	public String getDirector() {
		/**
		 * get the director
		 * @return String Director
		 */
		return Director;
	}

	public ArrayList<String> getCast() {
		/**
		 * get the cast
		 * @return ArrayList<String> Cast
		 */
		return Cast;
	}

	public double getOverallRating() {
		/**
		 * get the overall rating
		 * @return double OverallRating
		 */
		return OverallRating;
	}

	public ArrayList<Review> getReview() {
		/**
		 * get review
		 * @return ArrayList<Review> pastReviews
		 */
		return pastReviews;
	}

	public MovieType getMovieType() {
		/**
		 * get type of movie
		 * @return MovieType Movie type
		 */
		return this.MovieType;
	}

	// setter methods
	public void setMovieTitle(String MovieTitle) {
		/**
		 * set the movie title
		 * @param String MovieTitle
		 */
		this.MovieTitle = MovieTitle;
	}

	public void setShowingStatus(String showingStatus) {
		/**
		 * set the showing status
		 * @param String showingStatus
		 */
		this.ShowingStatus = ShowingStatus.valueOf(showingStatus);
	}

	public void setMovieType(String movieType) {
		/**
		 * set the movie type
		 * @param String movieType
		 */
		this.MovieType = MovieType.valueOf(movieType);
	}

	public void setSynopsis(String Synopsis) {
		/**
		 * set the synopsis
		 * @param String Synopsis
		 */
		this.Synopsis = Synopsis;
	}

	public void setDirector(String Director) {
		/**
		 * set the director
		 * @param String Director
		 */
		this.Director = Director;
	}

	public void setCast(ArrayList<String> Cast) {
		/**
		 * set the cast
		 * @param ArrayList<String> Cast
		 */
		this.Cast = Cast;
	}

	public void calculateOverallRating() {
		/**
		 * calculate the overall rating of the movie
		 */
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

	public void setReview(ArrayList<Review> pastReviews) {
		/**
		 * set the array list of review
		 * @param ArrayList<Review> pastReviews
		 */
		this.pastReviews = pastReviews;
	}

	public void insertReview(Review review) {
		/**
		 * insert the individual review
		 * @param Review review
		 */
		this.pastReviews.add(review);
	}

	// other methods
	public void dumpDetails() {
		/**
		 * dumps the movie details into the console log
		 */
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