package view;

import java.util.ArrayList;
import java.util.Scanner;

import commons.Cinema;
import commons.Cinema.CinemaType;
import commons.Cinema.MovieRating;
import commons.CineplexState;
import commons.CineplexStateManager;
import movie.Movie.MovieType;
/**
 * menu for admin, CRUD selections for movie showtime
 */
public class AdminMovieShowtime extends View {
	Scanner sc = new Scanner(System.in);
	@Override
	protected int options() {
		/**
		 * print the various options for admin to select
		 */
        System.out.println("+--------------------------------------------------------+");
		System.out.println("1) Create movie Showtime");
		System.out.println("2) Read movie Showtime");
		System.out.println("3) Update movie Showtime");
		System.out.println("4) Delete movie Showtime");
		System.out.println("5) Previous menu");
		return 5;
	}

	@Override
	protected void runMenu() {
		/**
		 * choices for admin to select create, read or delete
		 */
		CineplexStateManager cineplexStateManager = CineplexStateManager.getInstance();
		int choice = getInput(options());
		switch (choice) {
		case 1:
			createMovieShowtime(cineplexStateManager);
			break;
		case 2:
			readMovieShowtime(cineplexStateManager);
			break;
		case 3:
			updateMovieShowtime(cineplexStateManager);
			break;
		case 4:
			deleteMovieShowtime(cineplexStateManager);
			break;
		case 5:
			getPrevView();
			break;
		default:
			System.out.println("Please input a valid integer choice");
		}

	}

	private void readMovieShowtime(CineplexStateManager cineplexStateManager) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter which Cineplex: ");
		String cineplexName = sc.nextLine();
		CineplexState cineplexState = cineplexStateManager.readCineplexState(cineplexName);
		for(String movieName: cineplexState.listMovies()){
			System.out.println(movieName);
		}
		System.out.println("Please enter movie name: ");
		String movieName = sc.nextLine();
		System.out.println("Please enter movie type (IMAX, ThreeD, Blockbuster): ");
		String movieType = sc.nextLine();
		System.out.println("Please enter cinema type (Regular, Platinum, Goldclass): ");
		String cinemaType = sc.nextLine();
		for(Cinema cinema : cineplexState.findShowTime(movieName, MovieType.valueOf(movieType), CinemaType.valueOf(cinemaType))){
			cinema.dumpDetails();
		}
	}

	private void createMovieShowtime(CineplexStateManager cineplexStateManager) {
		/**
		 * create a showtime for the movie listing
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Cineplex location: ");
		String cineplexLocation = sc.nextLine();

		System.out.println("Enter movie name: ");
		String movieName = sc.nextLine();

		MovieType movieType = inputMovieType();

		System.out.println("Enter Showtime: ");
		int showTime = sc.nextInt();

		// cinema details
		System.out.println("Enter Cinema uniqueID: ");
		sc.nextLine();
		String cinemaId = sc.nextLine();

		System.out.println("Enter Cinema number rows: ");
		int numRows = sc.nextInt();

		System.out.println("Enter Cinema number cols: ");
		int numCols = sc.nextInt();

		System.out.println("Enter Cinema type: ");
		System.out.println("Options include : Regular, Platinum, Goldclass");
		sc.nextLine();
		CinemaType cinemaType = CinemaType.valueOf(sc.nextLine());

		System.out.println("Enter movie rating type: ");
		System.out.println("Options include : PG, NC, Mature, Rated");
		MovieRating movieRating = MovieRating.valueOf(sc.nextLine());

		Cinema cinemaInsert = new Cinema(numRows, numCols, showTime, cinemaId, cinemaType, movieRating);
		cineplexStateManager.insertCineplexShowtime(cineplexLocation, movieName, cinemaInsert, movieType,cinemaType);

		System.out.println("Movie showtime added!");
	}

	private MovieType inputMovieType() {
		/**
		 * enter the movie listing type
		 * @return MovieType
		 */
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

	private void updateMovieShowtime(CineplexStateManager cineplexStateManager) {
		/**
		 * update the movie listing showtime
		 * @param CineplexStateManager cineplexStageManager
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Cineplex location: ");
		ArrayList<String> cineplexNames = cineplexStateManager.listCineplex();
		System.out.printf("Options include: \n");
		for (String cineplexName : cineplexNames) {
			System.out.printf("%s\n", cineplexName);
		}
		System.out.printf("\n");

		String cineplexLocation = sc.nextLine();
		CineplexState cineplexState = cineplexStateManager.readCineplexState(cineplexLocation);
		for(String movieName: cineplexState.listMovies()){
			System.out.println(movieName);
		}
		System.out.println("Enter movie name: ");
		String movieName = sc.nextLine();

		System.out.println("Enter Movie type: ");
		System.out.println("Options include : IMAX, ThreeD, Blockbuster");
		MovieType movieType = MovieType.valueOf(sc.nextLine());

		System.out.println("Enter Cinema type: ");
		System.out.println("Options include : Regular, Platinum, Goldclass");
		CinemaType cinemaType = CinemaType.valueOf(sc.nextLine());

		System.out.println("Enter cinema Id: ");
		ArrayList<Cinema> cinemaList = cineplexStateManager.readCineplexState(cineplexLocation).findCinema(movieName,
				movieType,cinemaType);
		System.out.printf("Options include: ");
		for (Cinema cinema : cinemaList) {
			System.out.printf("%s", cinema.getUniqueId());
		}
		System.out.printf("\n");

		String cinemaId = sc.nextLine();

		System.out.println("Enter field to edit: ");
		System.out.println("Options include : showTime, movieRating, cinemaType");
		String key = sc.nextLine();

		System.out.println("Enter value to edit: ");
		String value = sc.nextLine();

		cineplexStateManager.updateCineplexShowtime(cineplexLocation, movieName, cinemaId, key, value, movieType,cinemaType);
		System.out.println(key + " updated!");
		for (Cinema cinema : cinemaList){
			if (cinema.getUniqueId().equals(cinemaId)){
				cinema.dumpDetails();
			}
		}
		System.out.println("+--------------------------------------------------------+");

	}

	private void deleteMovieShowtime(CineplexStateManager cineplexStateManager) {
		/**
		 * delete movie listing show time
		 * @param CineplexStateManager cineplexStateManager
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Cineplex location: ");

		ArrayList<String> cineplexNames = cineplexStateManager.listCineplex();
		System.out.printf("Options include:\n");

		for (String cineplexName : cineplexNames) {
			System.out.printf("%s\n", cineplexName);
		}
		System.out.printf("\n");

		String cineplexLocation = sc.nextLine();
		CineplexState cineplexState = cineplexStateManager.readCineplexState(cineplexLocation);
		for(String movieName: cineplexState.listMovies()){
			System.out.println(movieName);
		}
		System.out.println("Enter movie name: ");
		String movieName = sc.nextLine();

		System.out.println("Enter Movie type: ");
		System.out.println("Options include : IMAX, ThreeD, Blockbuster");
		MovieType movieType = MovieType.valueOf(sc.nextLine());

		System.out.println("Enter Cinema type: ");
		System.out.println("Options include : Regular, Platinum, Goldclass");
		CinemaType cinemaType = CinemaType.valueOf(sc.nextLine());

		System.out.println("Enter cinema Id: ");
		ArrayList<Cinema> cinemaList = cineplexStateManager.readCineplexState(cineplexLocation).findCinema(movieName,
				movieType,cinemaType);
		System.out.printf("Options include:");

		for (Cinema cinema : cinemaList) {
			System.out.printf("%s", cinema.getUniqueId());
		}
		System.out.printf("\n");

		String cinemaId = sc.nextLine();

		cineplexStateManager.deleteCineplexShowtime(cineplexLocation, movieName, cinemaId, movieType,cinemaType);
		System.out.println("CinemaID removed!");
		System.out.println("+--------------------------------------------------------+");
	}

}
