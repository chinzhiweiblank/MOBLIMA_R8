package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.CineplexStateManager;
import Model.Movie;
import Controller.MovieListingStateManager;

/**
 * View Class for moviegoer to view movie details or to book and purchase tickets
 */
public class MoviesMenu extends View {
	/**
	 * CineplexStateManager to manage cineplexes
	 */
	private CineplexStateManager cineplexStateManager = CineplexStateManager.getInstance();
	/**
	 * MovieListingStateManager to manage movie listings
	 */
	private MovieListingStateManager movieListingStateManager = MovieListingStateManager.getInstance();

	/**
	 * Prints out available options
	 * @return Number of options available
	 */
	@Override
	protected int options() {

		System.out.println("+--------------------------------------------------------+");
		System.out.println("1) View movie details");
		System.out.println("2) Book and purchase ticket");
		System.out.println("3) Previous menu");
		return 3;
	}

	/**
	 * Prints out choices for moviegoer to select
	 */
	@Override
	protected void runMenu() {

		// get the state manager for cineplex and movies
		ArrayList<String> movieList = movieListingStateManager.listMovies();

		printMovieList(movieList);
		Scanner sc = new Scanner(System.in);

		loop: while (true) {
			int choice = getInput(options());
			switch (choice) {
			case 1:
				Movie movie = inputMovie(movieListingStateManager,movieList);
				if (movie != null) {
					movie.dumpDetails();
				}
				break;
			case 2:
				display(this, new MovieBookingMenu());
				break;
			case 3:
				getPrevView();
				break loop;
			default:
				System.out.println("Please input a valid integer choice");
			}
		}
	}

	/**
	 * Input movie listings
	 * @param movieListingStateManager movie listing state manager
	 * @param movieList list of the movies
	 * @return Movie movieSearched if successful, null if unsuccessful
	 */
	private Movie inputMovie(MovieListingStateManager movieListingStateManager,ArrayList<String> movieList) {


		printMovieList(movieList);

		Scanner sc = new Scanner(System.in);
		System.out.println("please input movie choice");
		int choice = sc.nextInt();


		// check whether movie is present
		Movie movieSearched = movieListingStateManager.searchMovie(movieList.get(choice-1));
		if (movieSearched == null) {
			System.out.println("Error. No such movie available. Please try again.");
			return null;
		} else {
			return movieSearched;
		}
	}



	/**
	 * Prints out the movie listings
	 * @param movieList list of the movies
	 */
	private void printMovieList(ArrayList<String> movieList){

		System.out.println("Movies listed:");
		System.out.println("");
		if (movieList.isEmpty()){
			System.out.println("None");
			return;
		}
		for(int i = 0 ;i<movieList.size() ;i++){
			System.out.printf("%d)\t%s\n", i+1, movieList.get(i));
		}
		System.out.println("");
	}

}
