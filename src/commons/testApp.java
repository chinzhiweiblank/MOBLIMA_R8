package commons;

import java.util.ArrayList;

import commons.Cinema.CinemaType;
import commons.Cinema.MovieRating;
import movie.Movie;
import movie.Movie.MovieType;
import movie.MovieListingStateManager;
import movie.Review;
import movie.ShowingStatus;

public class testApp {

	public static void main(String[] args) {

		// Serialize some cineplex data
		CineplexStateManager cineplexStateManager = CineplexStateManager.getInstance();
		Cinema hall1 = new Cinema(10, 8, 1700, "001", CinemaType.Regular, MovieRating.NC);
		cineplexStateManager.insertCineplexShowtime("GV tampines", "WALL-E", hall1, MovieType.Blockbuster,CinemaType.Regular);

		Cinema hall2 = new Cinema(10, 8, 1500, "002", CinemaType.Goldclass, MovieRating.NC);
		cineplexStateManager.insertCineplexShowtime("GV tampines", "WALL-E", hall2, MovieType.Blockbuster,CinemaType.Goldclass);

		Cinema hall3 = new Cinema(10, 8, 1700, "001", CinemaType.Platinum, MovieRating.NC);
		cineplexStateManager.insertCineplexShowtime("GV tampines", "Spiderman", hall3, MovieType.IMAX,CinemaType.Platinum);

		Cinema hall4 = new Cinema(10, 8, 1500, "002", CinemaType.Goldclass, MovieRating.NC);
		cineplexStateManager.insertCineplexShowtime("GV tampines", "Spiderman", hall4, MovieType.ThreeD,CinemaType.Goldclass);

		Cinema hall5 = new Cinema(10, 8, 1900, "001", CinemaType.Goldclass, MovieRating.NC);
		cineplexStateManager.insertCineplexShowtime("Shaw bedok", "WALL-E", hall5, MovieType.Blockbuster,CinemaType.Goldclass);

		Cinema hall6 = new Cinema(10, 8, 2100, "002", CinemaType.Regular, MovieRating.PG);
		cineplexStateManager.insertCineplexShowtime("Shaw bedok", "WALL-E", hall6, MovieType.ThreeD,CinemaType.Regular);

		Cinema hall7 = new Cinema(10, 8, 2300, "001", CinemaType.Platinum, MovieRating.PG);
		cineplexStateManager.insertCineplexShowtime("Shaw bedok", "Infinity wars", hall7, MovieType.IMAX,CinemaType.Platinum);

		Cinema hall8 = new Cinema(10, 8, 0000, "002", CinemaType.Regular, MovieRating.PG);
		cineplexStateManager.insertCineplexShowtime("Shaw bedok", "Infinity wars", hall8, MovieType.IMAX,CinemaType.Regular);
		cineplexStateManager.serialize();

		// serialize some movielisting state data
		MovieListingStateManager movieListingManager = MovieListingStateManager.getInstance();

		ArrayList<String> walleCast = new ArrayList<String>();
		walleCast.add("MJ");
		walleCast.add("Rihanna");
		walleCast.add("Lebron");
		ArrayList<Review> walleReview = new ArrayList<>();
		Review review1 = new Review("Anthony", "WALL-E", 5.0, "1234", "Great movie!");
		Review review2 = new Review("Zhi Wei", "WALL-E", 3.0, "1234", "Great movie too!");
		Review review3 = new Review("Jovan", "WALL-E", 4.0, "1234", "Great movie yeah!");
		walleReview.add(review1);
		walleReview.add(review2);
		walleReview.add(review3);
		Movie walle = new Movie("WALL-E", ShowingStatus.Preview, "A robot trying to save the earth", "Miley Cyrus",
				walleCast, 3.0, walleReview, MovieType.Blockbuster);
		movieListingManager.createListing(walle.getMovieTitle(), walle, 100.0);

		ArrayList<String> spidermanCast = new ArrayList<>();
		spidermanCast.add("MJ");
		spidermanCast.add("Rihanna");
		spidermanCast.add("Lebron");
		ArrayList<Review> spidermanReview = new ArrayList<>();
		Review review4 = new Review("Anthony", "WALL-E", 1.0, "1504", "Great movie!");
		Review review5 = new Review("Zhi Wei", "WALL-E", 2.0, "1224", "Bad movie too!");
		Review review6 = new Review("Jovan", "WALL-E", 3.0, "0424", "Shit show yeah!");
		spidermanReview.add(review4);
		spidermanReview.add(review5);
		spidermanReview.add(review6);
		Movie spiderman = new Movie("Spiderman", ShowingStatus.Now_showing, "lousy show", "Phil jackson", spidermanCast,
				4.0, spidermanReview, MovieType.ThreeD);
		movieListingManager.createListing(spiderman.getMovieTitle(), spiderman, 500.0);

		ArrayList<String> infinityWarsCast = new ArrayList<>();
		infinityWarsCast.add("MJ");
		infinityWarsCast.add("Rihanna");
		infinityWarsCast.add("Lebron");
		ArrayList<Review> infinityWarsReview = new ArrayList<>();
		Review review7 = new Review("Anthony", "WALL-E", 5.0, "1234", "Sup[erb movie!");
		Review review8 = new Review("Zhi Wei", "WALL-E", 5.0, "1234", "Awesome too!");
		Review review9 = new Review("Jovan", "WALL-E", 4.0, "1234", "Great  yeah!");
		infinityWarsReview.add(review7);
		infinityWarsReview.add(review8);
		infinityWarsReview.add(review9);
		Movie infinityWars = new Movie("Infinity wars", ShowingStatus.Now_showing, "A robot trying to save the earth",
				"Miley Cyrus", infinityWarsCast, 5.0, infinityWarsReview, MovieType.Blockbuster);
		movieListingManager.createListing(infinityWars.getMovieTitle(), infinityWars, 1000.0);
		movieListingManager.serialize();

		// serialize some accounts
		AccountStateManager accountStateManager = AccountStateManager.getInstance();

		Booking newBooking1 = new Booking("WALL-E", "1", "GV tampines", 1, 3, 1234, "anthony@ntu.sg", "anthony",
				"98209339", 12, MovieType.Blockbuster,CinemaType.Platinum);
		Booking newBooking2 = new Booking("Spiderman", "1", "Shaw bedok", 1, 3, 1234, "anthony@ntu.sg", "anthony",
				"98209339", 15, MovieType.Blockbuster,CinemaType.Platinum);
		Booking newBooking3 = new Booking("WALL-E", "2", "GV tampines", 2, 4, 1330, "zhiwei@ntu.sg", "Zhiwei",
				"99999999", 10, MovieType.Blockbuster,CinemaType.Goldclass);
		Booking newBooking4 = new Booking("Spiderman", "2", "Shaw bedok", 1, 3, 1234, "zhiwei@ntu.sg", "Zhiwei",
				"99999999", 13, MovieType.Blockbuster,CinemaType.Regular);
		ArrayList<Booking> anthonyBooking = new ArrayList<Booking>();
		anthonyBooking.add(newBooking1);
		anthonyBooking.add(newBooking2);

		ArrayList<Booking> zwBooking = new ArrayList<Booking>();
		zwBooking.add(newBooking3);
		zwBooking.add(newBooking4);

		MovieGoer anthonyMovieGoer = new MovieGoer("Anthony", "Fong", anthonyBooking, "98209339", "anthony@ntu.sg",
				"anthony", spidermanReview, 22);
		MovieGoer zwMovieGoer = new MovieGoer("Zhiwei", "chin", zwBooking, "99999999", "zhiwei@ntu.sg", "Zhiwei",
				infinityWarsReview, 22);
		AdminUser siHan = new AdminUser("si", "han", "sihan@ntu.sg", "sihan");
		accountStateManager.createAccount(anthonyMovieGoer);
		accountStateManager.createAccount(zwMovieGoer);
		accountStateManager.createAccount(siHan);
		accountStateManager.serialize();

		// add in configuration files
		ConfigurationStateManager configurationStateManager = ConfigurationStateManager.getInstance();
		configurationStateManager.setMaxAdultAge(16);
		configurationStateManager.setMaxAdultAge(65);
		configurationStateManager.insertHoliday("20191112");
		configurationStateManager.serialize();
	}

}
