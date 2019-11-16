package movie;

import commons.MovieGoer;

/**
 * review manager class
 */
public class ReviewManager implements IReviewManager {
	private Review reviewObj;
	private MovieListingStateManager MovieListingStateManager;
	private MovieGoer movieGoer;

	public ReviewManager() {
		/**
		 * constructor for reviewManager
		 */
	}

	public ReviewManager(Review reviewObj, MovieListingStateManager MovieListingStateManager, MovieGoer movieGoer) {
		/**
		 * constructor for reviewManager
		 * @param Review reviewObj
		 * @param MovieListingStateManager MovieListingStateManager
		 * @param MovieGoer movieGoer
		 */
		this.reviewObj = reviewObj;
		this.MovieListingStateManager = MovieListingStateManager;
		this.movieGoer = movieGoer;
	}

	public Review getReview() {
		/**
		 * get review
		 * @return Review reviewObj
		 */
		return reviewObj;
	}

	public void setReview(Review reviewObj) {
		/**
		 * set review
		 * @param Review reviewObj
		 */
		this.reviewObj = reviewObj;
	}

	@Override
	public void submitReview() {
		/**
		 * submit review
		 */
		// Function need to add review object into movielisting manager and the person
		// class
		Movie movie = this.MovieListingStateManager.readListing(this.reviewObj.getMovieName());
		movie.insertReview(this.reviewObj);

		// insert review into movieGoer
		this.movieGoer.insertReview(this.reviewObj);
	}

}