package view;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import commons.CineplexStateManager;
import movie.Movie;
import movie.MovieListingStateManager;
import movie.ShowingStatus;

public class MoviesMenu extends View {
	private CineplexStateManager cineplexStateManager = CineplexStateManager.getInstance();
	private MovieListingStateManager movieListingStateManager = MovieListingStateManager.getInstance();

	@Override
	protected int options() {
		System.out.println("----------------------------");
		System.out.println("1) View movie details");
		System.out.println("2) Book and purchase ticket");
		System.out.println("3) previous menu");
		return 3;
	}

	@Override
	protected void runMenu() {
		// get the state manager for cineplex and movies

		ArrayList<String> movieList = movieListingStateManager.listMovies();
		printMovieList(movieList);


		loop: while (true) {
			int choice = getInput(options());
			switch (choice) {
			case 1:
				Movie movie = inputMovie(movieListingStateManager);
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
				System.out.println("Unknown error occurred");
			}
		}

	}

	// helper functions
	private Movie inputMovie(MovieListingStateManager movieListingStateManager) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input a movie: ");
		String movieinput = sc.nextLine();
//		sc.close();

		// check whether movie is present
		Movie movieSearched = movieListingStateManager.searchMovie(movieinput);
		if (movieSearched == null) {
			System.out.println("Error. No such movie available. Please try again.");
			return null;
		} else {
			return movieSearched;
		}
	}

	private void printMovieList(ArrayList<String> movieList){
		System.out.println("Movies currently showing:");
		System.out.println("");
		if (movieList.isEmpty()){
			System.out.println("None");
			return;
		}
		for(int i = 0 ;i<movieList.size() ;i++){
			System.out.printf("%d)\t%s\n", i, movieList.get(i));
		}
		System.out.println("");
	}

}
