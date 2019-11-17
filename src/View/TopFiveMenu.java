package View;

import java.util.ArrayList;

import Model.Movie;
import Controller.MovieListingStateManager;
/**
 * Menu for moviegoer to see top 5 movies by sales and rating
 */
public class TopFiveMenu extends View {

	/**
	 * MovieListingStateManager to manage the Movie Listing
	 */
	private MovieListingStateManager movieListingStateManager = MovieListingStateManager.getInstance();

	/**
	 * Prints out available options
	 * @return Number of options available
	 */
	@Override
	protected int options() {

		System.out.println("+--------------------------------------------------------+");
		System.out.println("1) Top 5 movies by ticket sales");
		System.out.println("2) Top 5 movies by overall rating");
		System.out.println("3) Previous menu");
		return 3;
	}

	/**
	 * Prints out choices for moviegoer to select
	 */
	@Override
	protected void runMenu() {

		int choice = getInput(options());
		switch (choice) {
		case 1:
			System.out.println("Top 5 movies by ticket sales");
			System.out.println("----------------------------");
			ArrayList<Movie> topFiveSales = movieListingStateManager.listTopFiveSales();
			for (int i = 0; i < topFiveSales.size(); i++) {
				Movie movieDisplay = topFiveSales.get(i);
				System.out.printf("%d) %s %.2f\n", i + 1, movieDisplay.getMovieTitle(),
						movieListingStateManager.readSalesDictionary(movieDisplay.getMovieTitle()));
			}
			break;
		case 2:
			System.out.println("Top 5 movies by overall rating");
			System.out.println("----------------------------");
			ArrayList<Movie> topFiveRating = movieListingStateManager.listTopFiveRatings();
			for (int i = 0; i < topFiveRating.size(); i++) {
				Movie movieDisplay = topFiveRating.get(i);
				System.out.printf("%d) %s %.2f\n", i + 1, movieDisplay.getMovieTitle(), movieDisplay.getOverallRating());
			}
			break;
		case 3:
			getPrevView();
			break;
		default:
			System.out.println("Please input a valid integer choice");
		}
	}
}