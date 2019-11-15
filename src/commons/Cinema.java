package commons;

public class Cinema implements java.io.Serializable {

	private int numberOfRows;
	private int numberOfCols;
	private int showTime;
	private String uniqueId;
	private int[][] cinemaSeating;
	private CinemaType cinemaType;
	private MovieRating movieRating;

	public enum CinemaType {
		Regular, Platinum, Goldclass
	}

	public enum MovieRating {
		PG, NC, Mature, Rated
	}

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

	// getter methods
	public int getshowTime() {
		return this.showTime;
	}

	public int getRow() {
		return this.numberOfRows;
	}

	public int getCol() {
		return this.numberOfCols;
	}

	public String getUniqueId() {
		return this.uniqueId;
	}

	public MovieRating getMovieRating() {
		return this.movieRating;
	}

	public CinemaType getCinemaType() {
		return this.cinemaType;
	}

	// setter methods
	public void setshowTime(int showTime) {
		this.showTime = showTime;
	}

	public void setUniqueId(String ID) {
		this.uniqueId = ID;
	}

	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
	}

	public void setCinemaType(CinemaType cinemaType) {
		this.cinemaType = cinemaType;
	}

	public int checkAvailable(int row, int col) {
		if((row < this.numberOfRows) && (col < this.numberOfCols)){
			System.out.println("Seat is not in the available range. Please try again.");
			return 0;
		}
		if (this.cinemaSeating[row][col] == 1) {
			// not available
			return 0;
		} else {
			// available
			return 1;
		}
	}

	public void printSeating() {
		System.out.println("====Screen====");
		for (int i = 0; i < this.cinemaSeating.length; i++) {
			for (int j = 0; j < this.cinemaSeating[i].length; j++) {
				if (j == this.cinemaSeating[i].length / 2) {
					System.out.print(" ");
					System.out.print(this.cinemaSeating[i][j]);
				} else {
					System.out.print(this.cinemaSeating[i][j]);
				}
			}
			System.out.println();
		}
	}

	public int updateSeating(int row, int col) {
		if (checkAvailable(row, col) == 0) {
			// seat not available, did not update seating
			return 0;
		} else {
			this.cinemaSeating[row][col] = 1;
			return 1;
		}
	}

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
