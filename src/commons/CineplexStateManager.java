package commons;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import movie.Movie.MovieType;

public class CineplexStateManager implements java.io.Serializable {

	private static CineplexStateManager singleton_instance = null;

	private Hashtable<String, CineplexState> cineplexStateMulti; // key is movie, value is cinema etc

	// singleton initialisation
	private CineplexStateManager() {
		this.cineplexStateMulti = new Hashtable<String, CineplexState>();
	}

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

	// functions used for booking
	public ArrayList<String> listMoviesShowing() {
		ArrayList<String> movieList = new ArrayList<String>();
		// loop through every cineplex and aggregrate the results
		this.cineplexStateMulti.forEach((k, v) -> {
			ArrayList<String> tempList = v.listMoviesShowing();
			for (String movieName : tempList) {
				if (!movieList.contains(movieName)) {
					movieList.add(movieName);
				}
			}
		});
		return movieList;
	}

	public ArrayList<String> listCineplex() {
		ArrayList<String> cineplexNames = new ArrayList<String>();
		Set<String> cineplexNamesSet = this.cineplexStateMulti.keySet();
		for (String cineplexName : cineplexNamesSet) {
			cineplexNames.add(cineplexName);
		}
		return cineplexNames;
	}

	public boolean displayShowTime(String movieName, MovieType movieType) {
		Set<String> cineplexNames = this.cineplexStateMulti.keySet();

		System.out.printf("Movie Selected: %s\n", movieName);

		System.out.println("Location | cinema showtime");
		System.out.println("--------------------------");
		boolean haveShowTime = false;

		for (String cineplexName : cineplexNames) {
			CineplexState cineplexState = this.cineplexStateMulti.get(cineplexName);
			ArrayList<Cinema> showtimeArr = cineplexState.findShowTime(movieName, movieType);
			if (showtimeArr == null) {
				continue;
			}
			System.out.printf("%s:", cineplexName);
			haveShowTime = true;
			for (Cinema cinema : showtimeArr) {
				System.out.printf("%d    ", cinema.getshowTime());
			}
			System.out.println("");
		}
		if (!haveShowTime){
			System.out.println("No showtimes available for this movie type!");
		}
		return haveShowTime;
	}

	public void printSeatAvailability(String cineplexLocation, String movieName, MovieType movieType,
			Integer showTime) {
		if (!this.cineplexStateMulti.containsKey(cineplexLocation)) {
			System.out.println("There isnt such a cineplex");
			return;
		} else {
			CineplexState cineplexState = this.cineplexStateMulti.get(cineplexLocation);
			cineplexState.printSeatAvailability(movieName, showTime, movieType);
		}
	}

	public void printSeatAvailability(String cineplexLocation, String movieName, Integer showTime, String cinemaId,
			MovieType movieType) {
		if (!this.cineplexStateMulti.containsKey(cineplexLocation)) {
			System.out.println("There isnt such a cineplex");
			return;
		} else {
			CineplexState cineplexState = this.cineplexStateMulti.get(cineplexLocation);
			cineplexState.printSeatAvailability(movieName, showTime, cinemaId, movieType);
		}
	}

	public CineplexState readCineplexState(String CineplexName) {
		return this.cineplexStateMulti.get(CineplexName);
	}

	public boolean verifyCineplex(String Cineplex) {
		if (this.cineplexStateMulti.containsKey(Cineplex)) {
			return true;
		} else {
			return false;
		}
	}

	// CRUD operations given cineplexlocation, cinemaobject and moviename
	public void insertCineplexShowtime(String cineplexLocation, String movieName, Cinema cinema, MovieType movieType) {
		// create the key if not present
		if (!this.cineplexStateMulti.containsKey(cineplexLocation)) {
			CineplexState cineplexState = new CineplexState();
			this.cineplexStateMulti.put(cineplexLocation, cineplexState);
		}
		CineplexState cineplexState = this.cineplexStateMulti.get(cineplexLocation);
		cineplexState.insertCinemaShowtime(movieName, cinema, movieType);
	}

	public void updateCineplexShowtime(String cineplexLocation, String movieName, String cinemaId, String key,
			String value, MovieType movieType) {
		if (!this.cineplexStateMulti.containsKey(cineplexLocation)) {
			System.out.println("There isnt such a cineplex");
			return;
		}
		CineplexState cineplexState = this.cineplexStateMulti.get(cineplexLocation);
		cineplexState.updateCinemaShowtime(movieName, cinemaId, key, value, movieType);
	}

	public void deleteCineplexShowtime(String cineplexLocation, String movieName, String cinemaId,
			MovieType movieType) {
		if (!this.cineplexStateMulti.containsKey(cineplexLocation)) {
			System.out.println("There isnt such a cineplex");
			return;
		}
		CineplexState cineplexState = this.cineplexStateMulti.get(cineplexLocation);
		cineplexState.deleteCinemaShowtime(movieName, cinemaId, movieType);
	}

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
			System.out.println("ClassNotFoundException is caught");
		}

	};

}
