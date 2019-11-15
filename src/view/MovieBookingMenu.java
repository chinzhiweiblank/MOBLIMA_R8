package view;

import java.util.ArrayList;
import java.util.Scanner;

import commons.CineplexStateManager;
import movie.Movie.MovieType;

public class MovieBookingMenu extends View {

	Scanner sc = new Scanner(System.in);

	@Override
	protected int options() {
		System.out.println("----------------------------");
		System.out.println("1) Select Timing and Movietype");
		System.out.println("2) previous menu");
		return 2;
	}

	@Override
	protected void runMenu() {
		CineplexStateManager cineplexStateManager = CineplexStateManager.getInstance();

		String movieName = inputMovie(cineplexStateManager);
		MovieType movieType = inputMovieType();
		if(movieType==null){
			getPrevView(); return ;
		}
		cineplexStateManager.displayShowTime(movieName, movieType);
		int choice = 0;
		while (choice != 2) {
			choice = getInput(options());
			switch (choice) {
			case 1:
				display(this, new SeatsMenu(movieName, movieType));
				break;
			case 2:
				getPrevView();
				break;
			case -1:
				break;
			default:
				System.out.println("Unknown error occurred");
			}
		}

	}

	// other helper functions
	public String removeMovieType(String movieNameAndType) {
		int spaceIndex = movieNameAndType.indexOf("_");
		if (spaceIndex != -1) {
			String movieName = movieNameAndType.substring(0, spaceIndex);
			return movieName;
		}
		return movieNameAndType;
	}

	private String inputMovie(CineplexStateManager cineplexStateManager) {
		while (true) {
			System.out.println("Please input a movie: ");
			String movieInput = sc.nextLine();
			ArrayList<String> movieNames = cineplexStateManager.listMoviesShowing();

			for (String movieName : movieNames) {
				if (removeMovieType(movieName).equals(movieInput)) {
					return movieInput;
				}
			}
			System.out.println("Movie does not exist!");
		}
	}

	private MovieType inputMovieType() {
		while (true) {
			System.out.println("Enter movie type: ");
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
}
