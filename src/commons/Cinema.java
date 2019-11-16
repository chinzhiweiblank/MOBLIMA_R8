package commons;

/**
 * Model Class representing a cinema
 */
public class Cinema implements java.io.Serializable {

	/**
	 * Number of rows of seats in the cinema
	 */
	private int numberOfRows;
	/**
	 * Number of columns of seats in the cinema
	 */
	private int numberOfCols;
	/**
	 * Time of the movie shown
	 */
	private int showTime;
	/**
	 * ID of the cinema
	 */
	private String uniqueId;
	/**
	 * The array of the seats in the cinema
	 */
	private int[][] cinemaSeating;
	/**
	 * The type of the cinema
	 */
	private CinemaType cinemaType;
	/**
	 * Rating of the movie shown at the cinema
	 */
	private MovieRating movieRating;

	/**
	 * The types of cinemas available
	 */
	public enum CinemaType {
		Regular, Platinum, Goldclass
	}

	/**
	 * The type of ratings available
	 */
	public enum MovieRating {
		PG, NC, Mature, Rated
	}

	/**
	 * Constructor for the Cinema Class
	 * @param numberOfRows number of rows of seats
	 * @param numberOfCols number of columns of seats
	 * @param showTime the time of the movie shown
	 * @param uniqueId the id of the cinema
	 * @param cinemaType the type of the cinema
	 * @param movieRating the rating of the movie shown at the cinema
	 */
	public Cinema(int numberOfRows, int numberOfCols, int showTime, String uniqueId, CinemaType cinemaType,
			MovieRating movieRating) {
		this.numberOfRows = numberOfRows;
		this.numberOfCols = numberOfCols;
		this.showTime = showTime;
		this.uniqueId = uniqueId;
		this.cinemaSeating = new int[this.numberOfRows][this.numberOfCols];
		this.cinemaType = cinemaType;
		this.movieRating = movieRating;
	}

	/**
	 * Obtains the time of the movie shown at the cinema
	 * @return the time of the movie shown at the cinema
	 */
	public int getshowTime() {
		return this.showTime;
	}

	/**
	 * Obtains the number of rows of the seats in the cinema
	 * @return the number of rows of the seats in the cinema
	 */
	public int getRow() {
		return this.numberOfRows;
	}

	/**
	 * Obtains the number of columns of the seats in the cinema
	 * @return the number of columns of the seats in the cinema
	 */
	public int getCol() {
		return this.numberOfCols;
	}

	/**
	 * Obtains the id of the cinema
	 * @return the id of the cinema
	 */
	public String getUniqueId() {
		return this.uniqueId;
	}

	/**
	 * Obtains the rating of the movie shown at the cinema
	 * @return the rating of the movie shown at the cinema
	 */
	public MovieRating getMovieRating() {
		return this.movieRating;
	}

	/**
	 * Obtains the type of the cinema
	 * @return the type of the cinema
	 */
	public CinemaType getCinemaType() {
		return this.cinemaType;
	}

	/**
	 * Sets the time of the movie at the cinema
	 * @param showTime the time of the movie at the cinema
	 */
	public void setshowTime(int showTime) {
		this.showTime = showTime;
	}

	/**
	 * Sets the ID of the cinema
	 * @param ID the ID of the cinema
	 */
	public void setUniqueId(String ID) {
		this.uniqueId = ID;
	}

	/**
	 * Sets the rating of the movie shown at the cinema
	 * @param movieRating the rating of the movie shown at the cinema
	 */
	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
	}

	/**
	 * Sets the type of the cinema
	 * @param cinemaType the type of the cinema
	 */
	public void setCinemaType(CinemaType cinemaType) {
		this.cinemaType = cinemaType;
	}

	/**
	 * Checks whether a seat in a specified row and column is available in the cinema
	 * @param row row of the desired seat
	 * @param col column of the desired seat
	 * @return 1 if the seat is available and 0 otherwise or if the seat is out of range
	 */
	public int checkAvailable(int row, int col) {
		if((row > this.numberOfRows) || (col > this.numberOfCols) || (row < 1) || (col<1)){
			System.out.println("Seat is not in the available range. Please try again.");
			return 0;
		}
		if (this.cinemaSeating[row-1][col-1] == 1) {
			// not available
			return 0;
		} else {
			// available
			return 1;
		}
	}

	/**
	 * Prints a representation of the seating arrangement in the cinema
	 */
	public void printSeating() {
		System.out.println("===Screen===");
		for (int i = 0; i < this.cinemaSeating.length; i++) {
			for (int j = 0; j < this.cinemaSeating[i].length; j++) {
				if (j == this.cinemaSeating[i].length / 2) {
					System.out.print(" ");
					System.out.print(this.cinemaSeating[i][j]);
				} else {
					System.out.print(this.cinemaSeating[i][j]);
				}
			}
			System.out.print(" " + (i+1));
			System.out.println();
		}
		for (int k = 0; k < this.cinemaSeating[1].length; k++){
            if (k == this.cinemaSeating[1].length / 2) {
                System.out.print(" ");
                System.out.print(k+1);
            } else {
                System.out.print(k+1);
            }
        }
		System.out.println();
	}

	public void dumpDetails(){
		System.out.println("Cinema details:");
		System.out.println("Unique ID: " + this.getUniqueId());
		System.out.println("Showtimes: " + this.getshowTime());
		System.out.println("Age rating: " + this.getMovieRating());
		System.out.println("Cinema type: " + this.getCinemaType());
	}

	/**
	 * Updates the status of the status in a specified row and column in the cinema
	 * @param row row of the seat
	 * @param col column of the seat
	 * @return 1 if the update is successful and 0 otherwise
	 */
	public int updateSeating(int row, int col) {
		if (checkAvailable(row, col) == 0) {
			// seat not available, did not update seating
			return 0;
		} else {
			this.cinemaSeating[row-1][col-1] = 1;
			return 1;
		}
	}

	/**
	 * Compares an object with another object to see if they are the same using their references
	 * @param obj the object to be compared
	 * @return true if the object is the same and false otherwise
	 */
	@Override
	public boolean equals(Object obj) {

		// checking if both the object references are
		// referring to the same object.
		if (this == obj)
			return true;

		// it checks if the argument is of the
		// type Geek by comparing the classes
		// of the passed argument and this object.
		// if(!(obj instanceof Geek)) return false; ---> avoid.
		if (obj == null || obj.getClass() != this.getClass())
			return false;

		// type casting of the argument.
		Cinema cinema = (Cinema) obj;

		// comparing the state of argument with
		// the state of 'this' Object.
		return (cinema.numberOfRows == this.numberOfRows && cinema.numberOfCols == this.numberOfCols
				&& cinema.showTime == this.showTime && cinema.uniqueId.equals(this.uniqueId)
				&& java.util.Arrays.deepEquals(cinema.cinemaSeating, this.cinemaSeating)
				&& cinema.cinemaType == this.cinemaType && cinema.movieRating == this.movieRating);
	}

}
