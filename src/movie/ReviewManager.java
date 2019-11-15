package movie;

import commons.MovieGoer;

public class ReviewManager implements IReviewManager {
	private Review reviewObj;
	private MovieListingStateManager MovieListingStateManager;
	private MovieGoer movieGoer;

	public ReviewManager() {
	}

	public ReviewManager(Review reviewObj, MovieListingStateManager MovieListingStateManager, MovieGoer movieGoer) {
		this.reviewObj = reviewObj;
		this.MovieListingStateManager = MovieListingStateManager;
		this.movieGoer = movieGoer;
	}

	public Review getReview() {
		return reviewObj;
	}

	public void setReview(Review reviewObj) {
		this.reviewObj = reviewObj;
	}

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