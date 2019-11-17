package view;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import commons.Cinema;
import commons.CineplexStateManager;
import movie.Movie;
import movie.Movie.MovieType;
import movie.MovieListingStateManager;
import movie.ShowingStatus;

/**
 * menu for moviegoer to book tickets for movies
 */
public class MovieBookingMenu extends View {
	private MovieListingStateManager movieListingStateManager = MovieListingStateManager.getInstance();
	Scanner sc = new Scanner(System.in);

	@Override
	/**
	 * print the various options for moviegoer to select
	 */
	protected int options() {

		System.out.println("+--------------------------------------------------------+");
		System.out.println("1) Select Timing and Movietype");
		System.out.println("2) Previous menu");
		return 2;
	}

	@Override
	/**
	 * choices for moviegoer to select
	 */
	protected void runMenu() {

		CineplexStateManager cineplexStateManager = CineplexStateManager.getInstance();

		Hashtable<ShowingStatus,ArrayList<String>> movieList = cineplexStateManager.listMoviesShowing();

		Hashtable<Integer,String> userInputMapping = printMovieList(movieList);

		int userInput = sc.nextInt();

		String[] userInputString = userInputMapping.get(userInput).split("_");

		String movieName = userInputString[0];
		MovieType movieType = MovieType.valueOf(userInputString[1]);
		Cinema.CinemaType cinemaType = Cinema.CinemaType.valueOf(userInputString[2]);

		if(cineplexStateManager.displayShowTime(movieName, movieType, cinemaType)){
			int choice = 0;
			while (choice != 2) {
				choice = getInput(options());
				switch (choice) {
					case 1:
						display(this, new SeatsMenu(movieName, movieType, cinemaType));
						break;
					case 2:
						getPrevView();
						break;
					default:
						System.out.println("Please input a valid integer choice");
				}
			}
		} else {
			getPrevView();
		}


	}

	// other helper functions
	/**
	 * remove the movie listing type attribute
	 * @param movieNameAndType
	 * @return String movieName
	 */
	public String removeMovieType(String movieNameAndType) {

		int spaceIndex = movieNameAndType.indexOf("_");
		if (spaceIndex != -1) {
			String movieName = movieNameAndType.substring(0, spaceIndex);
			return movieName;
		}
		return movieNameAndType;
	}

	/**
	 * user to enter movie listing
	 * @param cineplexStateManager
	 * @return String movieInput if successful, null if unsuccessful
	 */
	private String inputMovie(CineplexStateManager cineplexStateManager) {

		boolean Showing = true;
		while (true) {
			System.out.println("Please input a movie: ");
			String movieInput = sc.nextLine();

			Hashtable<ShowingStatus,ArrayList<String>> movieNames = cineplexStateManager.listMoviesShowing();

			for (ShowingStatus showingStatus: movieNames.keySet()){
				ArrayList<String> movieNamesStringArr = movieNames.get(showingStatus);
				for (String movieNamesCheck :movieNamesStringArr ){
					if (removeMovieType(movieNamesCheck).equals(movieInput) && (showingStatus == ShowingStatus.Now_showing || showingStatus == ShowingStatus.Preview)) {
						return movieInput;
					} else if (showingStatus != ShowingStatus.Now_showing && showingStatus != ShowingStatus.Preview){
						Showing = false;
					}
				}
			}
			System.out.println("Movie does not exist!");
			if (!Showing){
				System.out.println("Movie is not showing!");
			} else {
				System.out.println("Movie does not exist!");
			}
			return null;
		}
	}

	/**
	 * user to enter movie listing type
	 * @return MovieType movieType
	 */
	private MovieType inputMovieType() {

		while (true) {
			System.out.println("Enter movie type(IMAX, ThreeD, Blockbuster): ");
			String movieTypeString = sc.nextLine();
			try {
				MovieType movieType = MovieType.valueOf(movieTypeString);
				return movieType;
			} catch (IllegalArgumentException exception) {
				System.out.println("movie type does not exist!");
				return null;
			}
		}
	}


	/**
	 * user to input cinema type
	 * @return Cinema.CinemaType cinemaType
	 */
	private Cinema.CinemaType inputCinemaType(){

		System.out.println("Enter cinema type: ");
		String cinemaType = sc.nextLine();
		return Cinema.CinemaType.valueOf(cinemaType);
	}



	/**
	 * print out the list of movie listings
	 * @param movieHash
	 * @return Hashtable<Integer, String> userInputMapping if there are movie listings, null if none
	 */
	private Hashtable<Integer,String> printMovieList(Hashtable<ShowingStatus,ArrayList<String>> movieHash) {


		Hashtable<Integer,String> userInputMapping = new Hashtable<Integer,String>();

		System.out.println("Movies: ");
		System.out.println("");
		if (movieHash.isEmpty()){
			System.out.println("None");
			return null;
		}
		int counter = 1;
		for (ShowingStatus showingStatus:movieHash.keySet()) {
			ArrayList<String> strings = movieHash.get(showingStatus);
			if (showingStatus != ShowingStatus.End_Of_Showing) {
				System.out.println("Movies with showing status:" + showingStatus);
				for (int i = 0; i < strings.size(); i++) {
					System.out.printf("%d)\t%s\n", counter, strings.get(i));
					userInputMapping.put(counter,strings.get(i));
					counter++;
				}
				System.out.println("");
			}
		}
		return userInputMapping;
	}
}
