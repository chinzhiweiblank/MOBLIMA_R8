package movie;

import java.text.DecimalFormat;
import java.util.ArrayList;

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
		return MovieTitle;
	}

	public ShowingStatus getShowingStatus() {
		return this.ShowingStatus;
	}

	public String getSynopsis() {
		return Synopsis;
	}

	public String getDirector() {
		return Director;
	}

	public ArrayList<String> getCast() {
		return Cast;
	}

	public double getOverallRating() {
		return OverallRating;
	}

	public ArrayList<Review> getReview() {
		/**
		 * ArrayList<Review> ReviewCopy = new ArrayList<Review>(); for(Review review :
		 * pastReviews) {ReviewCopy.add(review);} return ReviewCopy;
		 */
		return pastReviews;
	}

	public MovieType getMovieType() {
		return this.MovieType;
	}

	// setter methods
	public void setMovieTitle(String MovieTitle) {
		this.MovieTitle = MovieTitle;
	}

	public void setShowingStatus(String showingStatus) {
		this.ShowingStatus = ShowingStatus.valueOf(showingStatus);
	}

	public void setMovieType(String movieType) {
		this.MovieType = MovieType.valueOf(movieType);
	}

	public void setSynopsis(String Synopsis) {
		this.Synopsis = Synopsis;
	}

	public void setDirector(String Director) {
		this.Director = Director;
	}

	public void setCast(ArrayList<String> Cast) {
		this.Cast = Cast;
	}

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

	public void setReview(ArrayList<Review> pastReviews) {
		this.pastReviews = pastReviews;
	}

	public void insertReview(Review review) {
		this.pastReviews.add(review);
	}

	// other methods
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
		System.out.println("----------------------------");
	}
}