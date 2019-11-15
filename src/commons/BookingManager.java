package commons;

import movie.MovieListingStateManager;
import movie.ShowingStatus;

public class BookingManager implements IBookingManager {

	private Booking booking;
	private CineplexStateManager cineplexStateManager;
	private MovieListingStateManager movieListingStateManager;
	private MovieGoer movieGoer;

	public BookingManager(Booking booking, CineplexStateManager cineplexStateManager, MovieGoer movieGoer,
			MovieListingStateManager movieListingStateManager) {
		this.booking = booking;
		this.cineplexStateManager = cineplexStateManager;
		this.movieGoer = movieGoer;
		this.movieListingStateManager = movieListingStateManager;
	}

	@Override
	public int bookTickets() {
		ShowingStatus movieStatus = this.movieListingStateManager.readListing(this.booking.getMovie())
				.getShowingStatus();

		if (movieStatus == ShowingStatus.Coming_Soon || movieStatus == ShowingStatus.End_Of_Showing) {
			System.out.println("Movie is coming soon or no longer showing");
			return 0;
		}

		// need to edit at both places movie goer and cineplexstatemanager
		CineplexState cineplexState = this.cineplexStateManager.readCineplexState(this.booking.getCineplex());
		int success = cineplexState.updateSeating(this.booking);

		if (success == 0) {
			return success;
		}

		// update user history and sales
		this.movieGoer.insertBooking(this.booking);
		double sales = this.movieListingStateManager.readSalesDictionary(this.booking.getMovie());
		if (sales < 0) {
			// movie not found
			return 0;
		} else {
			// update the price
			sales = sales + this.booking.getPrice();
			this.movieListingStateManager.UpdateSalesDictionary(this.booking.getMovie(), sales);
		}
		return success;
	}
}
