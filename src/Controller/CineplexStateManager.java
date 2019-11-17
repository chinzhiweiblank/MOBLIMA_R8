package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import Model.Cinema;
import Model.CineplexState;
import Model.Movie.MovieType;
import Model.ShowingStatus;

/**
 * Controller class managing the cineplex and its state
 */
public class CineplexStateManager implements java.io.Serializable {

	/**
	 * Changes the cineplex state manager into a singleton
	 */
	private static CineplexStateManager singleton_instance = null;

	/**
	 * The states of the cineplex
	 */
	private Hashtable<String, CineplexState> cineplexStateMulti; // key is movie, value is cinema etc

	/**
	 * Constructor of the CineplexStateManager singleton
	 */
	private CineplexStateManager() {
		this.cineplexStateMulti = new Hashtable<String, CineplexState>();
	}

	/**
	 * Obtains an instance of the CineplexStateManager
	 * @return an instance of the CineplexStateManager and ensures only one thread is able to create the singleton
	 */
	public static CineplexStateManager getInstance() {
		if (singleton_instance == null) {

			// Synchronised ensures that only one thread is able to create the singleton
			synchronized (CineplexStateManager.class) {
				singleton_instance = new CineplexStateManager();
//				singleton_instance.deserialize();
			}
		}
		return singleton_instance;
	}

	/**
	 * Iterates through each state of the cineplexes and updates the status of the movies shown at the cineplexes
	 * @return a HashTable representing the status of the movies shown at the cineplexes
	 */
	public Hashtable<ShowingStatus,ArrayList<String>> listMoviesShowing() {
		Hashtable<ShowingStatus,ArrayList<String>> movieHash = new Hashtable<ShowingStatus,ArrayList<String>>();
		// loop through every cineplex and aggregrate the results
		this.cineplexStateMulti.forEach((cineplexName, cineplexState) -> {
			Hashtable<ShowingStatus,ArrayList<String>> tempHash = cineplexState.listMoviesShowing();
			tempHash.forEach((showingStatus,tempHashStringArr)->{
				for (String movieName : tempHashStringArr){
					// if there is no showing status we create an empty string list
					if (!movieHash.containsKey(showingStatus)){
						ArrayList<String> movieHashStringArr = new ArrayList<String>();
						movieHashStringArr.add(movieName);
						movieHash.put(showingStatus,movieHashStringArr);
					}  else {
						ArrayList<String> movieHashStringArr = movieHash.get(showingStatus);
						if (!movieHashStringArr.contains(movieName)){
							movieHashStringArr.add(movieName);
							movieHash.put(showingStatus,movieHashStringArr);
						}
					}
				}
			});
		});
		return movieHash;
	}

	/**
	 * Obtains a list of name of the cineplexes
	 * @return a list of names of the cineplexes
	 */
	public ArrayList<String> listCineplex() {
		ArrayList<String> cineplexNames = new ArrayList<String>();
		Set<String> cineplexNamesSet = this.cineplexStateMulti.keySet();
		for (String cineplexName : cineplexNamesSet) {
			cineplexNames.add(cineplexName);
		}
		return cineplexNames;
	}

	/**
	 * Displays the showtime of movie, its type at a certain cinema type
	 * @param movieName the name of the movie
	 * @param movieType the type of the movie
	 * @param cinemaType the type of the cinema
	 * @return true if there is a showtime and false otherwise
	 */
	public boolean displayShowTime(String movieName, MovieType movieType, Cinema.CinemaType cinemaType) {
		MovieListingStateManager movieListingStateManager = MovieListingStateManager.getInstance();

		Set<String> cineplexNames = this.cineplexStateMulti.keySet();

		System.out.printf("Movie Selected: %s\n", movieName);

		System.out.println("Location | cinema showtime");
		System.out.println("--------------------------");
		boolean haveShowTime = false;
		if (movieListingStateManager.readListing(movieName).getShowingStatus() == ShowingStatus.Coming_Soon ||
				movieListingStateManager.readListing(movieName).getShowingStatus() == ShowingStatus.End_Of_Showing){
			System.out.println("No showtimes available for this movie type!");
			return false;
		}
		for (String cineplexName : cineplexNames) {
			CineplexState cineplexState = this.cineplexStateMulti.get(cineplexName);
			ArrayList<Cinema> showtimeArr = cineplexState.findShowTime(movieName, movieType,cinemaType);
			if (showtimeArr == null) {
				continue;
			}
			System.out.printf("%s:", cineplexName);
			haveShowTime = true;
			for (Cinema cinema : showtimeArr) {
				System.out.printf("%d, %s", cinema.getshowTime(),cinema.getCinemaType().toString());
			}
			System.out.println("");
		}

		if (!haveShowTime){
			System.out.println("No showtimes available for this movie type!");
		}
		return haveShowTime;
	}

	/**
	 * Prints the seat availability of a movie of specified name, type, time at a specified cinema type at a specified
	 * cineplex location
	 * @param cineplexLocation the location of a cineplex
	 * @param movieName the name of a movie
	 * @param movieType the type of a movie
	 * @param showTime the time a movie is shown
	 * @param cinemaType the type of cinema
	 */
	public void printSeatAvailability(String cineplexLocation, String movieName, MovieType movieType,
									  Integer showTime, Cinema.CinemaType cinemaType) {
		if (!this.cineplexStateMulti.containsKey(cineplexLocation)) {
			System.out.println("There isn't such a cineplex");
			return;
		} else {
			CineplexState cineplexState = this.cineplexStateMulti.get(cineplexLocation);
			cineplexState.printSeatAvailability(movieName, showTime, movieType, cinemaType);
		}
	}

	/**
	 * Prints the seat availability of a movie of specified name, type, time at a specified cinema ID and type
	 * at a specified cineplex location
	 * @param cineplexLocation the location of a cineplex
	 * @param movieName the name of a movie
	 * @param showTime the time of the movie is shown
	 * @param cinemaId the ID of the cinema
	 * @param movieType the type of the movie
	 * @param cinemaType the type of the cinema
	 */
	public void printSeatAvailability(String cineplexLocation, String movieName, Integer showTime, String cinemaId,
									  MovieType movieType, Cinema.CinemaType cinemaType) {
		if (!this.cineplexStateMulti.containsKey(cineplexLocation)) {
			System.out.println("There isnt such a cineplex");
			return;
		} else {
			CineplexState cineplexState = this.cineplexStateMulti.get(cineplexLocation);
			cineplexState.printSeatAvailability(movieName, showTime, cinemaId, movieType, cinemaType);
		}
	}

	/**
	 * Obtains the state of a cineplex by name
	 * @param CineplexName the name of the cineplex
	 * @return the state of the cineplex
	 */
	public CineplexState readCineplexState(String CineplexName) {
		return this.cineplexStateMulti.get(CineplexName);
	}

	/**
	 * Verifies whether a cineplex exists by name
	 * @param Cineplex the name of the cineplex
	 * @return True if the cineplex by the specified name exists and False otherwise
	 */
	public boolean verifyCineplex(String Cineplex) {
		if (this.cineplexStateMulti.containsKey(Cineplex)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Inserts showtimes into the state of the cineplex
	 * @param cineplexLocation the location of the cineplex
	 * @param movieName the title of the movie
	 * @param cinema the name of the cinema
	 * @param movieType the type of the movie shown
	 * @param cinemaType the type of cinema
	 */
	public void insertCineplexShowtime(String cineplexLocation, String movieName, Cinema cinema,
									   MovieType movieType, Cinema.CinemaType cinemaType) {
		// create the key if not present
		if (!this.cineplexStateMulti.containsKey(cineplexLocation)) {
			CineplexState cineplexState = new CineplexState();
			this.cineplexStateMulti.put(cineplexLocation, cineplexState);
		}
		CineplexState cineplexState = this.cineplexStateMulti.get(cineplexLocation);
		cineplexState.insertCinemaShowtime(movieName, cinema, movieType,cinemaType);
	}

	/**
	 * Updates the showtime in the state of the cineplex
	 * @param cineplexLocation the location of the cineplex
	 * @param movieName the title of the movie
	 * @param cinemaId the ID of the cinema
	 * @param key the key of the cineplex location desired
	 * @param value the value of the cineplex location desired
	 * @param movieType type of movie
	 * @param cinemaType type of cinema
	 */
	public void updateCineplexShowtime(String cineplexLocation, String movieName, String cinemaId, String key,
									   String value, MovieType movieType, Cinema.CinemaType cinemaType) {
		if (!this.cineplexStateMulti.containsKey(cineplexLocation)) {
			System.out.println("There isn't such a cineplex");
			return;
		}
		CineplexState cineplexState = this.cineplexStateMulti.get(cineplexLocation);
		cineplexState.updateCinemaShowtime(movieName, cinemaId, key, value, movieType,cinemaType);
	}

	/**
	 * Delete cinema showtime from a specified cineplex and cinema movietype and cinema id
	 * @param cineplexLocation location of the cineplex
	 * @param movieName title of the movie
	 * @param cinemaId ID of the cinema
	 * @param movieType the type of the movie
	 * @param cinemaType the type of the cinema
	 */
	public void deleteCineplexShowtime(String cineplexLocation, String movieName, String cinemaId,
									   MovieType movieType, Cinema.CinemaType cinemaType ) {
		if (!this.cineplexStateMulti.containsKey(cineplexLocation)) {
			System.out.println("There isnt such a cineplex");
			return;
		}
		CineplexState cineplexState = this.cineplexStateMulti.get(cineplexLocation);
		cineplexState.deleteCinemaShowtime(movieName, cinemaId, movieType,cinemaType);
	}

	/**
	 * Converts the state of an object into a byte stream of data
	 */
	public void serialize() {

		try {
			String filename = "data/CineplexState.ser";
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			// Method for serialization of object
			out.writeObject(this);

			out.close();
			file.close();

		}

		catch (IOException exceptionMessage) {
			System.out.println(exceptionMessage);
		}
	};

	/**
	 * Converts a byte stream of data into an object for storage
	 */
	public void deserialize() {
		// Deserialization
		try {
			// Reading the object from a file
			String filename = "data/CineplexState.ser";
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);

			// Method for deserialization of object
			CineplexStateManager deserializeObj = (CineplexStateManager) in.readObject();

			in.close();
			file.close();

			this.cineplexStateMulti = deserializeObj.cineplexStateMulti;
		}

		catch (IOException exceptionMessage) {
			System.out.println(exceptionMessage);
		}

		catch (ClassNotFoundException exceptionMessage) {
			System.out.println("cineplex state manager");
			System.out.println("ClassNotFoundException is caught");
		}

	};

}
