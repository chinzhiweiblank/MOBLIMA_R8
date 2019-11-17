package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Model.Cinema;
import Model.CineplexState;
import Controller.CineplexStateManager;
import Model.Movie;
import Model.Movie.MovieType;
import Controller.MovieListingStateManager;
import Model.Review;
import Model.ShowingStatus;
/**
 * Controller class to manage admin menu movie listing
 */
public class AdminMovieListing extends View {

	/**
	 * MovieListingStateManager to manage Movie Listings
	 */
	private MovieListingStateManager movieListingStateManager;
	private CineplexStateManager cineplexStateManager;

	/**
	 * Prints out available options
	 * @return Number of options available
	 */
	@Override
	protected int options() {

		System.out.println("+--------------------------------------------------------+");
		System.out.println("1) Create movie listing");
		System.out.println("2) Read movie listing");
		System.out.println("3) Update movie Listing");
		System.out.println("4) Delete movie Listing");
		System.out.println("5) Previous menu");
		return 5;
	}

	/**
	 * Prints out choices for admin to select create, read or delete
	 */
	@Override
	protected void runMenu() {

		loop: while (true) {
			int choice = getInput(options());
			movieListingStateManager = MovieListingStateManager.getInstance();
			cineplexStateManager = CineplexStateManager.getInstance();
			switch (choice) {
			case 1:
				Movie movieAdd = createMovieListing();
				movieListingStateManager.createListing(movieAdd.getMovieTitle(), movieAdd, 0.0);
				System.out.println("Movie listing created!");
				break;
			case 2:
				readMovieListing();
				break;
			case 3:
				updateMovieListing();
				break;
			case 4:
				deleteMovieListing();
				break;
			case 5:
				getPrevView();
				break loop;
			default:
				System.out.println("Please input a valid integer choice");
			}
		}
	}

	/**
	 * Reads movie listing
	 */
	private void readMovieListing() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter movie name to display: ");
		String movieName = sc.nextLine();
		boolean found = false;
		MovieListingStateManager movieListingStateManager = MovieListingStateManager.getInstance();
		for(String listingName: movieListingStateManager.getListing()){
			if(movieName.equals(listingName)){
				movieListingStateManager.readListing(listingName).dumpDetails();
				found = true;
			}
		}

		if (!found){
			System.out.println("Movie cant be found");
		}
	}

	/**
	 * Delets a particular movie listing
	 */
	private void deleteMovieListing() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie name: ");
		String movieName = sc.nextLine();
		movieListingStateManager.deleteListing(movieName);
		System.out.printf("%s has been successfully deleted\n",movieName);

		//loop through all cinplex location
		for (String cineplexLocation : cineplexStateManager.listCineplex()){
			CineplexState cineplexState = cineplexStateManager.readCineplexState(cineplexLocation);
			for (Cinema.CinemaType cinemaType : Cinema.CinemaType.values()){
				for (MovieType movieType:MovieType.values()){
					cineplexState.deleteCinemaShowtime(movieName,movieType,cinemaType);
				}
			}
		}
	}

	/**
	 * Updates the movie listing
	 */
	private void updateMovieListing() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie name: ");
		String movieName = sc.nextLine();

		System.out.println("Enter field to modify: ");
		System.out.println("Options include : ShowingStatus, Synopsis, Director, movieType");
		String field = sc.nextLine();

		System.out.println("Enter value to modify: ");
		if (field.equals("ShowingStatus")) {
			System.out.println("Options include : Coming_Soon, Preview, Now_showing, End_Of_Showing");
		} else if (field.equals("movieType")) {
			System.out.println("Options include : IMAX, ThreeD, Blockbuster");
		}
		String value = sc.nextLine();
		movieListingStateManager.updateListing(movieName, field, value);

		System.out.printf("%s has been successfully modified\n",value);
	}

	/**
	 * Create a new movie listing
	 * @return Movie of the movie listing
	 */
	private Movie createMovieListing() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie name: ");
		String movieName = sc.nextLine();

		System.out.println("Enter Movie showing status: ");
		System.out.println("Options include : Coming_Soon, Preview, Now_showing, End_Of_Showing");

		ShowingStatus movieStatus = ShowingStatus.valueOf(sc.nextLine());

		System.out.println("Enter Movie synopsis: ");
		String movieSynopsis = sc.nextLine();

		System.out.println("Enter Movie director: ");
		String movieDirector = sc.nextLine();

		System.out.println("Enter Movie cast (separated by comma): ");
		String movieCast = sc.nextLine();
		String[] movieCastArr = movieCast.split(",");
		ArrayList<String> movieCastArrlist = new ArrayList<String>(Arrays.asList(movieCastArr));

		double movieOverallRating = 0.0;
		ArrayList<Review> newReviews = new ArrayList<Review>();

		System.out.println("Enter Movie type: ");
		System.out.println("Options include : IMAX, ThreeD, Blockbuster");
		MovieType movieType = MovieType.valueOf(sc.nextLine());

		System.out.println("Enter Sales: ");
		double sales = sc.nextDouble();

		Movie movie = new Movie(movieName, movieStatus, movieSynopsis, movieDirector, movieCastArrlist, movieOverallRating,
				newReviews, movieType);
		if(movieListingStateManager.createListing(movieName, movie, sales)==1){
			System.out.println("Movie Listing successfully entered");
		}
		else{
			System.out.println("Movie Listing Entry Unsuccessful");
		}

		return movie;
	}

}
