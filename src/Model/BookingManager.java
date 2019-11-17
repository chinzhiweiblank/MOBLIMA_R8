package Model;

import Controller.CineplexStateManager;
import Controller.MovieListingStateManager;

/**
 * Represents the controller class managing the Bookings
 */
public class BookingManager implements IBookingManager {
	/**
	 * Booking object representing the booking managed by the controller
	 */
	private Booking booking;

	/**
	 * Controller class managing the cineplexes
	 */
	private CineplexStateManager cineplexStateManager;
	/**
	 * Controller class managing the movie listings
	 */
	private MovieListingStateManager movieListingStateManager;
	/**
	 * Moviegoer who made the booking
	 */
	private MovieGoer movieGoer;

	/**
	 * Constructor of the BookingManager class
	 * @param booking Booking made by the moviegoer
	 * @param cineplexStateManager Cineplex in the booking
	 * @param movieGoer Moviegoer making the booking
	 * @param movieListingStateManager MovieListing for booking
	 */
	public BookingManager(Booking booking, CineplexStateManager cineplexStateManager, MovieGoer movieGoer,
			MovieListingStateManager movieListingStateManager) {
		this.booking = booking;
		this.cineplexStateManager = cineplexStateManager;
		this.movieGoer = movieGoer;
		this.movieListingStateManager = movieListingStateManager;
	}

	/**
	 * The booking of tickets
	 * @return 1 if booking is successful and 0 otherwise
	 */
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
