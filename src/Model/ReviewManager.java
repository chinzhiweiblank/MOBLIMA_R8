package Model;

import Controller.MovieListingStateManager;

/**
 * review manager class
 */
public class ReviewManager implements IReviewManager {
	private Review reviewObj;
	private Controller.MovieListingStateManager MovieListingStateManager;
	private MovieGoer movieGoer;

	/**
	 * constructor for reviewManager
	 */
	public ReviewManager() {

	}

	/**
	 * constructor for reviewManager
	 * @param reviewObj review
	 * @param MovieListingStateManager movie listing state manager
	 * @param movieGoer movie goer
	 */
	public ReviewManager(Review reviewObj, MovieListingStateManager MovieListingStateManager, MovieGoer movieGoer) {

		this.reviewObj = reviewObj;
		this.MovieListingStateManager = MovieListingStateManager;
		this.movieGoer = movieGoer;
	}

	/**
	 * get review
	 * @return Review reviewObj
	 */
	public Review getReview() {

		return reviewObj;
	}

	/**
	 * set review
	 * @param reviewObj review
	 */
	public void setReview(Review reviewObj) {

		this.reviewObj = reviewObj;
	}

	/**
	 * submit review
	 */
	@Override
	public void submitReview() {
		// Function need to add review object into movielisting manager and the person
		// class
		Movie movie = this.MovieListingStateManager.readListing(this.reviewObj.getMovieName());
		movie.insertReview(this.reviewObj);

		// insert review into movieGoer
		this.movieGoer.insertReview(this.reviewObj);
	}

}