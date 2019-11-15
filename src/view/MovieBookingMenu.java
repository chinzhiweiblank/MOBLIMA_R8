package view;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import commons.Cinema;
import commons.CineplexStateManager;
import movie.Movie.MovieType;
import movie.ShowingStatus;

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
		Cinema.CinemaType cinemaType = inputCinemaType();
		if(movieType==null){
			getPrevView(); return ;
		}
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
					case -1:
						break;
					default:
						System.out.println("Unknown error occurred");
				}
			}
		} else {
			getPrevView();
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
			if (!Showing){
				System.out.println("Movie is not showing!");
			} else {
				System.out.println("Movie does not exist!");
			}
		}
	}

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


	private Cinema.CinemaType inputCinemaType(){
		System.out.println("Enter cinema type: ");
		String cinemaType = sc.nextLine();
		return Cinema.CinemaType.valueOf(cinemaType);
	}
}
