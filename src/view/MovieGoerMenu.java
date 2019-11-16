package view;

import java.util.ArrayList;

import commons.AccountType;
import commons.Booking;
import commons.MovieGoer;
import commons.OverallStateManager;

/**
 * menu for moviegoer to search movies, view booking history or list top 5 movies
 */
public class MovieGoerMenu extends View {

	private OverallStateManager overallStateManager = OverallStateManager.getInstance();

	@Override
	protected int options() {
		/**
		 * print the various options for moviegoer to select
		 */
		System.out.println("+--------------------------------------------------------+");
		System.out.println("1) Search/List movie");
		System.out.println("2) View booking history");
		System.out.println("3) List top 5 movies");
		System.out.println("4) Return to main");
		return 4;
	}

	@Override
	protected void runMenu() {
		/**
		 * choices for moviegoer to select
		 */
		int choice = 0;
		while (choice != 4) {
			choice = getInput(options());
			switch (choice) {
			case 1:
				display(this, new MoviesMenu());
				break;
			case 2:
				// if user has not logged in yet
				if (overallStateManager.getloginStatus(AccountType.USER)) {
					MovieGoer movieGoer = (MovieGoer) overallStateManager.getCurrentUserAccount(AccountType.USER);
					ArrayList<Booking> bookingList = movieGoer.getBookings();
					for (Booking booking : bookingList) {
						booking.dumpInfo();
					}
				} else {
					display(this, new loginMenu(AccountType.USER));
				}
				break;
			case 3:
				display(this, new TopFiveMenu());
				break;
			case 4:

				break;
			case 5:
				getPrevView();
				break;
			default:
				System.out.println("Please input a valid integer choice");
			}
		}
	}
}
