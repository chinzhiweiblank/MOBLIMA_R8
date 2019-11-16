package movie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * movie listing state manager class, singleton class used for state management of all movie information
 */
public class MovieListingStateManager implements java.io.Serializable {

	/*
	 * Singleton class used for state management of all movie information
	 */

	private static MovieListingStateManager singleton_instance;
	private Hashtable<String, Movie> movieListingDict;

	private Hashtable<String, Double> movieSalesDict;

	private MovieListingStateManager() {
		/**
		 * constructor for MovieListingStateManager
		 */
		this.movieListingDict = new Hashtable<String, Movie>();
		this.movieSalesDict = new Hashtable<String, Double>();
	}

	public static MovieListingStateManager getInstance() {
		/**
		 * gets and check instance of singleton class
		 * @return MovieListingStateManager singleton_instance
		 */
		if (singleton_instance == null) {
			// Synchronised ensures that only one thread is able to create the singleton
			synchronized (MovieListingStateManager.class) {
				singleton_instance = new MovieListingStateManager();
//				singleton_instance.deserialize();
			}
		}
		return singleton_instance;
	}

	public ArrayList<String> getListing(){
		/**
		 * get movie listing
		 * @return ArrayList<String> listArray
		 */
		ArrayList<String> listArray = new ArrayList<String>();
		this.movieListingDict.forEach((key, value) -> {
			listArray.add(key);
		});
		return listArray;
	}

	// CRUD operations
	public int createListing(String listing, Movie movie, Double sales) {
		/**
		 * create the movie listing
		 * @param String listing
		 * @param Movie movie
		 * @param Double sales
		 * @return int 1 if successful, 0 if unsuccessful
		 */
		try {
			this.movieListingDict.put(listing, movie);
			this.movieSalesDict.put(listing, sales);
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Movie readListing(String listing) {
		/**
		 * read the move listing
		 * @param String Listing
		 * @return Movie movieListingDict.get(listing) if successful, null if unsuccessful
		 */
		if (this.movieListingDict.containsKey(listing)) {
			return this.movieListingDict.get(listing);
		} else {
			System.out.println("Listing " + listing + " is not found");
			return null;
		}
	}

	public void printListing(){
		/**
		 * print listing
		 */
		this.movieListingDict.forEach((key, value) -> {
			System.out.println(key);
			value.dumpDetails();
		});
	}

	public int updateListing(String listing, Movie movie) {
		/**
		 * update the movie listings
		 * @param String listing
		 * @param Movie movie
		 * @return int 1 if successful, 0 if unsuccessful
		 */
		try {
			if (this.movieListingDict.containsKey(listing)) {
				System.out.println("Listing already exists.");
			} else {
				System.out.println("Listing does not exist.");
			}
			this.movieListingDict.put(listing, movie);
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int updateListing(String listing, String field, String value) {
		/**
		 * update the movie listings
		 * @param String listing
		 * @param String field
		 * @param String value
		 * @return int 1 if successful, 0 if unsuccessful
		 */
		try {
			if (this.movieListingDict.containsKey(listing)) {
				Movie movie = this.movieListingDict.get(listing);

				if (field.equals("ShowingStatus")) {
					movie.setShowingStatus(value);
				} else if (field.equals("Synopsis")) {
					movie.setSynopsis(value);
				} else if (field.equals("Director")) {
					movie.setDirector(value);
				} else if (field.equals("movieType")) {
					movie.setMovieType(value);
				} else {
					System.out.println("field is not present in movie data object");
				}
				return 1;
			} else {
				System.out.println("Listing does not exist.");
				return 0;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int deleteListing(String listing) {
		/**
		 * deletes the movie listing
		 * @param String listing
		 * @return int 1 if successful, 0 if unsuccessful
		 */
		try {
			if (this.movieListingDict.containsKey(listing)) {
				this.movieListingDict.remove(listing);
			} else {
				System.out.println("Listing does not exist.");
			}
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public void CreateSalesDictionary(String listing, Double sales) {
		/**
		 * create the sales dictionary
		 * @param String listing
		 * @param Double sales
		 */
		if (movieSalesDict.containsKey(listing)) {
			System.out.println("Listing already exist.");
			return;
		}
		movieSalesDict.put(listing, sales);
		System.out.printf("Created Listing: %s, Sales: %d.\n", listing, sales);
	}

	public double readSalesDictionary(String listing) {
		/**
		 * read the sales dictionary
		 * @param String listing
		 * @return double movieSalesDict.get(listing) if successful, -1 if not successful
		 */
		if (!movieSalesDict.containsKey(listing)) {
			System.out.println("Listing does not exist.");
			return -1;
		} else {
			return this.movieSalesDict.get(listing);
		}

	}

	public void UpdateSalesDictionary(String listing, double sales) {
		/**
		 * update sales dictionary
		 * @param String listing
		 * @param double sales
		 */
		if (!movieSalesDict.containsKey(listing)) {
			System.out.println("Listing does not exist.");
			return;
		}
		movieSalesDict.put(listing, sales);
	}

	// other operations
	public Movie searchMovie(String listing) {
		/**
		 * search for the movie in listing
		 * @param String listing
		 * @return Movie movieListingDict.get(listing) if successful, null if not successful
		 */
		try {
			if (movieListingDict.containsKey(listing)) {
				return this.movieListingDict.get(listing);
			} else {
				System.out.println("Movie not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ArrayList<Movie> listTopFiveSales() {
		/**
		 * list the top 5 movies sorted by sales
		 * @return ArrayList<Movie> sorted Movies
		 */
		ArrayList<String> sortedString = new ArrayList<String>();
		Set<String> keys = this.movieSalesDict.keySet();
		for (String movieName : keys) {
			// add in first element
			inserted: {
				if (sortedString.size() == 0) {
					sortedString.add(movieName);
					break inserted;
				}
				// Uses insertion sort to add the movie into the right place
				for (int i = 0; i < sortedString.size(); i++) {
					// movie to be inserted is larger than value
					if (this.movieSalesDict.get(movieName) > this.movieSalesDict.get(sortedString.get(i))) {
						sortedString.add(i, movieName);
						// keep only five elements
						if (sortedString.size() > 5) {
							sortedString.remove(5);
						}
						break inserted;
					}
				}
				if (sortedString.size() < 5) {
					sortedString.add(movieName);
				}
			}
		}

		// convert array of strings to array of movies
		ArrayList<Movie> sortedMovie = new ArrayList<Movie>();
		for (String stringIter : sortedString) {
			sortedMovie.add(this.movieListingDict.get(stringIter));
		}

		return sortedMovie;
	}

	public ArrayList<Movie> listTopFiveRatings() {
		/**
		 * list the top 5 movies sorted by ratings
		 * @return ArrayList<Movie> sorted movies
		 */
		ArrayList<Movie> sortedMovie = new ArrayList<Movie>();
		Set<String> keys = this.movieListingDict.keySet();
		for (String movieName : keys) {
			inserted: {
				// add in first element
				if (sortedMovie.size() == 0) {
					sortedMovie.add(this.movieListingDict.get(movieName));
					break inserted;
				}
				// Uses insertion sort to add the movie into the right place
				for (int i = 0; i < sortedMovie.size(); i++) {
					// movie to be inserted has larger rating
					if (this.movieListingDict.get(movieName).getOverallRating() > sortedMovie.get(i)
							.getOverallRating()) {
						sortedMovie.add(i, this.movieListingDict.get(movieName));
						// keep only five elements
						if (sortedMovie.size() > 5) {
							sortedMovie.remove(5);
						}
						break inserted;
					}
				}
				// add movie in if lest than five
				if (sortedMovie.size() < 5) {
					sortedMovie.add(this.movieListingDict.get(movieName));
				}
			}
		}

		return sortedMovie;
	}

	public ArrayList<String> listMovies() {
		/**
		 * movie list
		 * @return ArrayList<String> movieList
		 */
		ArrayList<String> moviesList = new ArrayList<String>();
		Set<String> keys = this.movieListingDict.keySet();
		for (String movieName : keys) {
			moviesList.add(movieName);
		}
		return moviesList;
	}


		public void serialize() {
			/**
			 * Serialize of object
			 */

		try {
			String filename = "data/MovieListingState.ser";
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
		/**
		 * Deserialization of object
		 */
		// Deserialization
		try {
			// Reading the object from a file
			String filename = "data/MovieListingState.ser";
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);

			// Method for deserialization of object
			MovieListingStateManager deserializeObj = (MovieListingStateManager) in.readObject();

			in.close();
			file.close();

			this.movieListingDict = deserializeObj.movieListingDict;
			this.movieSalesDict = deserializeObj.movieSalesDict;
		}

		catch (IOException exceptionMessage) {
		}

		catch (ClassNotFoundException exceptionMessage) {
			System.out.println("ClassNotFoundException is caught");
		}

	};

}