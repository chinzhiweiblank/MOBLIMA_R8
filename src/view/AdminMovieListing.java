package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import movie.Movie;
import movie.Movie.MovieType;
import movie.MovieListingStateManager;
import movie.Review;
import movie.ShowingStatus;

public class AdminMovieListing extends View {
	/**
	 * menu for admin, CRUD selections for movies
	 */
	private MovieListingStateManager movieListingStateManager;

	@Override
	protected int options() {
		/**
		 * print the various options for admin to select
		 */
		System.out.println("+--------------------------------------------------------+");
		System.out.println("1) Create movie listing");
		System.out.println("2) Update movie Listing");
		System.out.println("3) Delete movie Listing");
		System.out.println("4) Previous menu");
		return 4;
	}

	@Override
	protected void runMenu() {
		/**
		 * choices for admin to select create, read or delete
		 */
		loop: while (true) {
			int choice = getInput(options());
			movieListingStateManager = MovieListingStateManager.getInstance();
			switch (choice) {
			case 1:
				Movie movieAdd = createMovieListing();
				movieListingStateManager.createListing(movieAdd.getMovieTitle(), movieAdd, 0.0);
				System.out.println("Movie listing created!");
				break;
			case 2:
				updateMovieListing();
				break;
			case 3:
				deleteMovieListing();
				break;
			case 4:
				getPrevView();
				break loop;
			default:
				System.out.println("Please input a valid integer choice");
			}
		}
	}

	private void deleteMovieListing() {
		/**
		 * delete a particular movie listing
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie name: ");
		String movieName = sc.nextLine();
		movieListingStateManager.deleteListing(movieName);
	}

	private void showMovieListing() {
		/**
		 * show the various movie listings currently available
		 */

	}
	private void updateMovieListing() {
		/**
		 * update the movie listing
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie name: ");
		String movieName = sc.nextLine();

		System.out.println("Enter field to modify: ");
		System.out.println("options include : ShowingStatus, Synopsis, Director, movieType");
		String field = sc.nextLine();

		System.out.println("Enter value to modify: ");
		if (field.equals("ShowingStatus")) {
			System.out.println("options include : Coming_Soon, Preview, Now_showing, End_Of_Showing");
		} else if (field.equals("movieType")) {
			System.out.println("options include : IMAX, ThreeD, Blockbuster");
		}
		String value = sc.nextLine();
		movieListingStateManager.updateListing(movieName, field, value);

	}

	private Movie createMovieListing() {
		/**
		 * create a new movie listing
		 * @return Movie
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie name: ");
		String movieName = sc.nextLine();

		System.out.println("Enter Movie showing status: ");
		System.out.println("options include : Coming_Soon, Preview, Now_showing, End_Of_Showing");

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
		System.out.println("options include : IMAX, ThreeD, Blockbuster");
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
