package commons;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import commons.Cinema.CinemaType;
import commons.Cinema.MovieRating;
import movie.Movie.MovieType;
import movie.MovieListingStateManager;
import movie.ShowingStatus;
import movie.Movie;

public class CineplexState implements java.io.Serializable {

	private Hashtable<String, ArrayList<Cinema>> cineplexState; // key is movie, value is cinema etc
	private MovieListingStateManager movieListingManager;

	// singleton initialisation
	public CineplexState() {
		this.cineplexState = new Hashtable<String, ArrayList<Cinema>>();
	}

	// helper function to find the cinema from cinema hashtable
	private Cinema findCinema(Booking booking) {
		String movieNameAndType = booking.getMovie() + "_" + booking.getMovieType();
		ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
		for (Cinema cinema : cinemaList) {
			if (booking.getCinemaId().equals(cinema.getUniqueId())) {
				return cinema;
			}
		}
		// if no such cinema is found
		return null;
	}

	public ArrayList<Cinema> findCinema(String movieName, MovieType movieType) {
		String movieNameAndType = movieName + "_" + movieType.toString();
		ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
		return cinemaList;

	}

	// functions used to assist in booking
	public ArrayList<Cinema> findCinemaUsingTime(String movieName, Integer showTime, MovieType movieType) {
		String movieNameAndType = movieName + "_" + movieType.toString();
		ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
		ArrayList<Cinema> cinemaListReturn = new ArrayList<Cinema>();
		for (Cinema cinemaIter : cinemaList) {
			if (cinemaIter.getshowTime() == showTime) {
				cinemaListReturn.add(cinemaIter);
			}
		}
		// if no such cinema is found
		if (cinemaListReturn.size() == 0) {
			System.out.println("The cinema does not exist");
		}
		return cinemaListReturn;
	}

	public Cinema findCinemaUsingId(String movieName, String cinemaId, MovieType movieType) {
		String movieNameAndType = movieName + "_" + movieType.toString();
		ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
		for (Cinema cinemaIter : cinemaList) {
			if (cinemaIter.getUniqueId().equals(cinemaId)) {
				return cinemaIter;
			}
		}

		System.out.println("Cinema not found");
		return null;
	}

	public int checkSeatAvailability(Booking booking) {
		Cinema cinema = findCinema(booking);
		if (cinema.checkAvailable(booking.getRow(), booking.getCol()) == 1) {
			// seat is available
			return 1;
		} else {
			// seat is taken
			return 0;
		}
	}

	public int updateSeating(Booking booking) {
		Cinema cinema = findCinema(booking);
		return cinema.updateSeating(booking.getRow(), booking.getCol());
	}

	public void printSeatAvailability(Booking booking) {
		Cinema cinema = findCinema(booking);
		cinema.printSeating();
	}

	public void printSeatAvailability(String movieName, Integer showTime, MovieType movieType) {
		ArrayList<Cinema> cinema = findCinemaUsingTime(movieName, showTime, movieType);
		for (Cinema cinemaIter : cinema) {
			System.out.printf("Cinema | %s\n", cinemaIter.getUniqueId());
			cinemaIter.printSeating();
		}
	}

	public void printSeatAvailability(String movieName, Integer showTime, String CinemaId, MovieType movieType) {
		ArrayList<Cinema> cinema = findCinemaUsingTime(movieName, showTime, movieType);
		for (Cinema cinemaIter : cinema) {
			if (cinemaIter.getUniqueId() == CinemaId) {
				System.out.printf("Cinema | %s\n", cinemaIter.getUniqueId());
				cinemaIter.printSeating();
			}
		}
	}

	public ArrayList<Cinema> findShowTime(String movieName, MovieType movieType) {
		String movieNameAndType = movieName + "_" + movieType.toString();
		if (this.cineplexState.containsKey(movieNameAndType)) {
			ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
			return cinemaList;
		} else {
			return null;
		}
	}

	public ArrayList<String> listMoviesShowing() {
		this.movieListingManager = MovieListingStateManager.getInstance();
		ArrayList<String> movieList = new ArrayList<String>();
		Set<String> movieNames = this.cineplexState.keySet();
		for (String movieName : movieNames) {
			String movieNameOnly = removeMovieType(movieName);
			Movie movie = this.movieListingManager.readListing(movieNameOnly);
			if(movie==null){continue;}
			ShowingStatus movieStatus = movie.getShowingStatus();
			// only list movie if showing status is now showing or preview
			if (!movieList.contains(movieName)
					&& (movieStatus == ShowingStatus.Now_showing || movieStatus == ShowingStatus.Preview)) {
				movieList.add(movieName);
			}
		}
		return movieList;
	}

	// CRUD operations for cinema showtimes
	public void insertCinemaShowtime(String movieName, Cinema cinema, MovieType movieType) {
		String movieNameAndType = movieName + "_" + movieType.toString();
		if (this.cineplexState.containsKey(movieNameAndType)) {
			ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
			for (Cinema cinemaIter : cinemaList) {
				if (cinemaIter.equals(cinema)) {
					System.out.println("Cinema at this time already exist, skipping...");
					return;
				}
			}
			cinemaList.add(cinema);
		} else {
			ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
			cinemaList.add(cinema);
			this.cineplexState.put(movieNameAndType, cinemaList);
		}
	}

	public void updateCinemaShowtime(String movieName, String cinemaId, String key, String value, MovieType movieType) {
		Cinema cinemaEdit = findCinemaUsingId(movieName, cinemaId, movieType);
		if (cinemaEdit == null) {
			System.out.println("Cinema cannot be found, skipping...");
		}
		switch (key) {
		case ("showTime"):
			cinemaEdit.setshowTime(Integer.parseInt(value));
			break;
		case ("movieRating"):
			cinemaEdit.setMovieRating(MovieRating.valueOf(value));
			break;
		case ("cinemaType"):
			cinemaEdit.setCinemaType(CinemaType.valueOf(value));
			break;
		}
	}

	public void deleteCinemaShowtime(String movieName, String cinemaId, MovieType movieType) {
		String movieNameAndType = movieName + "_" + movieType.toString();
		ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
		Cinema cinemaRemove = findCinemaUsingId(movieName, cinemaId, movieType);
		if (cinemaRemove == null) {
			System.out.println("Cinema cannot be found, skipping...");
			return;
		}
		cinemaList.remove(findCinemaUsingId(movieName, cinemaId, movieType));
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
}
