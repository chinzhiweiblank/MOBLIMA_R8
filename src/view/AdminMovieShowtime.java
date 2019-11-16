package view;

import java.util.ArrayList;
import java.util.Scanner;

import commons.Cinema;
import commons.Cinema.CinemaType;
import commons.Cinema.MovieRating;
import commons.CineplexStateManager;
import movie.Movie.MovieType;

public class AdminMovieShowtime extends View {

	@Override
	protected int options() {
		System.out.println("----------------------------");
		System.out.println("1) Create movie Showtime");
		System.out.println("2) Update movie Showtime");
		System.out.println("3) Delete movie Showtime");
		System.out.println("4) Previous menu");
		return 4;
	}

	@Override
	protected void runMenu() {
		CineplexStateManager cineplexStateManager = CineplexStateManager.getInstance();
		int choice = getInput(options());
		switch (choice) {
		case 1:
			createMovieShowtime(cineplexStateManager);
			break;
		case 2:
			updateMovieShowtime(cineplexStateManager);
			break;
		case 3:
			deleteMovieShowtime(cineplexStateManager);
			break;
		case 4:
			getPrevView();
			break;
		default:
			System.out.println("Please input a valid integer choice");
		}

	}

	private void createMovieShowtime(CineplexStateManager cineplexStateManager) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Cineplex location: ");
		String cineplexLocation = sc.nextLine();

		System.out.println("Enter movie name: ");
		String movieName = sc.nextLine();

		System.out.println("Enter Movie type: ");
		System.out.println("options include : IMAX, ThreeD, Blockbuster");
		MovieType movieType = MovieType.valueOf(sc.nextLine());

		System.out.println("Enter Cinema number cols: ");
		int showTime = sc.nextInt();

		// cinema details
		System.out.println("Enter Cinema uniqueID: ");
		String cinemaId = sc.nextLine();

		System.out.println("Enter Cinema number rows: ");
		int numRows = sc.nextInt();

		System.out.println("Enter Cinema number cols: ");
		int numCols = sc.nextInt();

		System.out.println("Enter Cinema type: ");
		System.out.println("options include : Regular, Platinum, Goldclass");
		CinemaType cinemaType = CinemaType.valueOf(sc.nextLine());

		System.out.println("Enter movie rating type: ");
		System.out.println("options include : PG, NC, Mature, Rated");
		MovieRating movieRating = MovieRating.valueOf(sc.nextLine());

		Cinema cinemaInsert = new Cinema(numRows, numCols, showTime, cinemaId, cinemaType, movieRating);
		cineplexStateManager.insertCineplexShowtime(cineplexLocation, movieName, cinemaInsert, movieType,cinemaType);
	}

	private void updateMovieShowtime(CineplexStateManager cineplexStateManager) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Cinplex location: ");
		ArrayList<String> cineplexNames = cineplexStateManager.listCineplex();
		System.out.printf("options include:");

		for (String cineplexName : cineplexNames) {
			System.out.printf("%s", cineplexName);
		}
		System.out.printf("\n");

		String cineplexLocation = sc.nextLine();

		System.out.println("Enter movie name: ");
		String movieName = sc.nextLine();

		System.out.println("Enter Movie type: ");
		System.out.println("options include : IMAX, ThreeD, Blockbuster");
		MovieType movieType = MovieType.valueOf(sc.nextLine());

		System.out.println("Enter Cinema type: ");
		System.out.println("options include : Regular, Platinum, Goldclass");
		CinemaType cinemaType = CinemaType.valueOf(sc.nextLine());

		System.out.println("Enter cinema Id: ");
		ArrayList<Cinema> cinemaList = cineplexStateManager.readCineplexState(cineplexLocation).findCinema(movieName,
				movieType,cinemaType);
		System.out.printf("options include:");

		for (Cinema cinema : cinemaList) {
			System.out.printf("%s", cinema.getUniqueId());
		}
		System.out.printf("\n");

		String cinemaId = sc.nextLine();

		System.out.println("Enter field to edit: ");
		System.out.println("options include : showTime, movieRating, cinemaType");
		String key = sc.nextLine();

		System.out.println("Enter value to edit: ");
		String value = sc.nextLine();

		cineplexStateManager.updateCineplexShowtime(cineplexLocation, movieName, cinemaId, key, value, movieType,cinemaType);
	}

	private void deleteMovieShowtime(CineplexStateManager cineplexStateManager) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Cinplex location: ");

		ArrayList<String> cineplexNames = cineplexStateManager.listCineplex();
		System.out.printf("options include:");

		for (String cineplexName : cineplexNames) {
			System.out.printf("%s", cineplexName);
		}
		System.out.printf("\n");

		String cineplexLocation = sc.nextLine();

		System.out.println("Enter movie name: ");
		String movieName = sc.nextLine();

		System.out.println("Enter Movie type: ");
		System.out.println("options include : IMAX, ThreeD, Blockbuster");
		MovieType movieType = MovieType.valueOf(sc.nextLine());


		System.out.println("Enter Cinema type: ");
		System.out.println("options include : Regular, Platinum, Goldclass");
		CinemaType cinemaType = CinemaType.valueOf(sc.nextLine());

		System.out.println("Enter cinema Id: ");
		ArrayList<Cinema> cinemaList = cineplexStateManager.readCineplexState(cineplexLocation).findCinema(movieName,
				movieType,cinemaType);
		System.out.printf("options include:");

		for (Cinema cinema : cinemaList) {
			System.out.printf("%s", cinema.getUniqueId());
		}
		System.out.printf("\n");

		String cinemaId = sc.nextLine();

		cineplexStateManager.deleteCineplexShowtime(cineplexLocation, movieName, cinemaId, movieType,cinemaType);
	}

}
