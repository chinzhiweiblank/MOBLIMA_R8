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

/**
 * Singleton Class representing the state of a Cineplex
 */
public class CineplexState implements java.io.Serializable {

	private Hashtable<String, ArrayList<Cinema>> cineplexState; // key is movie, value is cinema etc
	private MovieListingStateManager movieListingManager;

	// singleton initialisation
	public CineplexState() {
		this.cineplexState = new Hashtable<String, ArrayList<Cinema>>();
	}

	/**
	 * Obtains a list of movies from the cineplex
	 * @return a list of movies from the cineplex
	 */
	public ArrayList<String> listMovies(){
		return new ArrayList<>(this.cineplexState.keySet());
	}

	/**
	 * Searches for a cinema using a booking
	 * @param booking the booking for the movie
	 * @return cinema available for the booking
	 */
	private Cinema findCinema(Booking booking) {
		String movieNameAndType = booking.getMovie() + "_" + booking.getMovieType() + "_" + booking.getCinemaType() ;
		ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
		for (Cinema cinema : cinemaList) {
			if (booking.getCinemaId().equals(cinema.getUniqueId())) {
				return cinema;
			}
		}
		// if no such cinema is found
		return null;
	}

	/**
	 * Obtains a list of cinemas using a movie title, type and cinema type
	 * @param movieName title of the movie
	 * @param movieType type of the movie
	 * @param cinemaType type of the cinema
	 * @return the list of cinemas available
	 */
	public ArrayList<Cinema> findCinema(String movieName, MovieType movieType,CinemaType cinemaType) {
		String movieNameAndType = movieName + "_" + movieType.toString() + "_" + cinemaType.toString();
		ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
		return cinemaList;
	}

	/**
	 * Searches for a cinema using a movie's showtime
	 * @param movieName title of the movie
	 * @param showTime the time when the movie is shown
	 * @param movieType the type of the movie
	 * @param cinemaType the type of the cinema
	 * @return the list of cinemas available
	 */
	public ArrayList<Cinema> findCinemaUsingTime(String movieName, Integer showTime, MovieType movieType,CinemaType cinemaType) {
		String movieNameAndType = movieName + "_" + movieType.toString() + "_" +cinemaType;
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

	/**
	 * Searches for a cinema using its ID
	 * @param movieName title of the movie
	 * @param cinemaId the ID of a cinema
	 * @param movieType the type of movie
	 * @param cinemaType the type of cinema
	 * @return cinema with the ID or null if not found
	 */
	public Cinema findCinemaUsingId(String movieName, String cinemaId, MovieType movieType,CinemaType cinemaType) {
		String movieNameAndType = movieName + "_" + movieType.toString() + "_" + cinemaType;
		ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
		for (Cinema cinemaIter : cinemaList) {
			if (cinemaIter.getUniqueId().equals(cinemaId) && cinemaIter.getCinemaType() == cinemaType) {
				return cinemaIter;
			}
		}

		System.out.println("Cinema not found");
		return null;
	}

	/**
	 * Checks whether a seat in the booking is available
	 * @param booking the booking made by the user
	 * @return 1 if the seat is available and 0 if it is not
	 */
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

	/**
	 * Updates the seating in the cinema using a booking
	 * @param booking the booking made by the user
	 * @return 1 if the seating is available and 0 if it is not
	 */
	public int updateSeating(Booking booking) {
		Cinema cinema = findCinema(booking);
		return cinema.updateSeating(booking.getRow(), booking.getCol());
	}

	/**
	 * Prints the seating arrangement in the cinema specified in the booking
	 * @param booking the booking made by the user
	 */
	public void printSeatAvailability(Booking booking) {
		Cinema cinema = findCinema(booking);
		cinema.printSeating();
	}

	public void printSeatAvailability(String movieName, Integer showTime, MovieType movieType,CinemaType cinemaType) {
		ArrayList<Cinema> cinema = findCinemaUsingTime(movieName, showTime, movieType,cinemaType);
		for (Cinema cinemaIter : cinema) {
			System.out.printf("Cinema | %s\n", cinemaIter.getUniqueId());
			cinemaIter.printSeating();
		}
	}

	/**
	 * Prints the seating arrangement using movie and cinema details
	 * @param movieName the title of a movie
	 * @param showTime the time of a show
	 * @param CinemaId the ID of the cinema
	 * @param movieType the type of movie
	 * @param cinemaType the type of cinema
	 */
	public void printSeatAvailability(String movieName, Integer showTime, String CinemaId, MovieType movieType,CinemaType cinemaType) {
		ArrayList<Cinema> cinema = findCinemaUsingTime(movieName, showTime, movieType,cinemaType);
		for (Cinema cinemaIter : cinema) {
			if (cinemaIter.getUniqueId() == CinemaId) {
				System.out.printf("Cinema | %s\n", cinemaIter.getUniqueId());
				cinemaIter.printSeating();
			}
		}
	}

	/**
	 * Searches the time of a show using the title of a movie, the type of a movie and the type of cinema
	 * and returns a list of cinemas
	 * @param movieName the title of the movie
	 * @param movieType the type of the movie
	 * @param cinemaType the type of the cinema
	 * @return the list of cinemas available
	 */
	public ArrayList<Cinema> findShowTime(String movieName, MovieType movieType, CinemaType cinemaType) {
		String movieNameAndType = movieName + "_" + movieType.toString() + "_" + cinemaType;
		if (this.cineplexState.containsKey(movieNameAndType)) {
			ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
			return cinemaList;
		} else {
			return null;
		}
	}


//	public ArrayList<String> listMoviesShowing() {
//		this.movieListingManager = MovieListingStateManager.getInstance();
//		ArrayList<String> movieList = new ArrayList<String>();
//		Set<String> movieNames = this.cineplexState.keySet();
//		for (String movieName : movieNames) {
//			String movieNameOnly = removeMovieType(movieName);
//			Movie movie = this.movieListingManager.readListing(movieNameOnly);
//			if(movie==null){continue;}
//			ShowingStatus movieStatus = movie.getShowingStatus();
//			// only list movie if showing status is now showing or preview
//			if (!movieList.contains(movieName)
//					&& (movieStatus == ShowingStatus.Now_showing || movieStatus == ShowingStatus.Preview)) {
//				movieList.add(movieName);
//			}
//		}
//		return movieList;
//	}

	/**
	 * Shows the list of movies that are showing and their status
	 * @return a HashTable in which the keys are the showing status of the movie and the values are the title of the movie
	 */
	public Hashtable<ShowingStatus,ArrayList<String>> listMoviesShowing() {
		this.movieListingManager = MovieListingStateManager.getInstance();
		Hashtable<ShowingStatus,ArrayList<String>> movieHash = new Hashtable<ShowingStatus,ArrayList<String>>();
		Set<String> movieNames = this.cineplexState.keySet();

		// loop through all movieNames
		for (String movieName : movieNames) {
			String movieNameOnly = removeMovieType(movieName);
			Movie movie = this.movieListingManager.readListing(movieNameOnly);
			if (movie == null) {
				continue;
			}
			ShowingStatus movieStatus = movie.getShowingStatus();

			//check if key in hashtable
			if (!movieHash.containsKey(movieStatus)) {
				ArrayList<String> movieStringArr = new ArrayList<String>();
				movieStringArr.add(movieName);
				movieHash.put(movieStatus, movieStringArr);
			} else {
				ArrayList<String> movieStringArr = movieHash.get(movieStatus);
				// if the movie name is not in the array
				if (!movieStringArr.contains(movieName)) {
					movieStringArr.add(movieName);
					movieHash.put(movieStatus, movieStringArr);
				}
			}
		}
		return movieHash;
	}

	// CRUD operations for cinema showtimes
	public void insertCinemaShowtime(String movieName, Cinema cinema, MovieType movieType, CinemaType cinemaType) {
		String movieNameAndType = movieName + "_" + movieType.toString()+ "_" + cinemaType.toString();
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

	public void updateCinemaShowtime(String movieName, String cinemaId, String key, String value, MovieType movieType, CinemaType cinemaType) {
		Cinema cinemaEdit = findCinemaUsingId(movieName, cinemaId, movieType,cinemaType);
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

	public void deleteCinemaShowtime(String movieName, String cinemaId, MovieType movieType, CinemaType cinemaType) {
		String movieNameAndType = movieName + "_" + movieType.toString() + "_" + cinemaType.toString();
		ArrayList<Cinema> cinemaList = this.cineplexState.get(movieNameAndType);
		Cinema cinemaRemove = findCinemaUsingId(movieName, cinemaId, movieType,cinemaType);
		if (cinemaRemove == null) {
			System.out.println("Cinema cannot be found, skipping...");
			return;
		}
		if (cinemaList.size() == 1){
			this.cineplexState.remove(movieNameAndType);
		} else {
			cinemaList.remove(findCinemaUsingId(movieName, cinemaId, movieType,cinemaType));
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
}
