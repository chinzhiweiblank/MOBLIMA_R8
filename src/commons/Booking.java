package commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import movie.Movie.MovieType;

public class Booking implements java.io.Serializable {

	private int Row;
	private int Col;
	private int showTime;
	private double price;
	private String mobileNumber;
	private String CinemaId;
	private String TID;
	private String Movie;
	private String Cineplex;
	private String email;
	private String name;
	private MovieType movieType;
	private Cinema.CinemaType cinemaType;

	public Booking(String Movie, String CinemaId, String Cineplex, int Row, int Col, int showTime, String email,
				   String name, String mobileNumber, double price, MovieType movieType, Cinema.CinemaType cinemaType) {
		this.Movie = Movie;
		this.Row = Row;
		this.Col = Col;
		this.CinemaId = CinemaId;
		this.Cineplex = Cineplex;
		this.TID = null;
		this.showTime = showTime;
		this.email = email;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.price = price;
		this.movieType = movieType;
		this.cinemaType = cinemaType;
		this.initTID();
	}

	// getter methods
	public String getMovie() {
		return this.Movie;
	}

	public String getCineplex() {
		return this.Cineplex;
	}

	public String getTID() {
		return this.TID;
	}

	public int getRow() {
		return this.Row;
	}

	public int getCol() {
		return this.Col;
	}

	public String getCinemaId() {
		return this.CinemaId;
	}

	public int getShowTime() {
		return this.showTime;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}

	public MovieType getMovieType() {
		return this.movieType;
	}

	public Cinema.CinemaType getCinemaType(){return this.cinemaType;}

	// setter methods
	public void setMovie(String movie) {
		this.Movie = movie;
	}

	public void initTID() {
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmm");
		String dateAppend = dateFormat.format(calendar.getTime());
		this.TID = this.CinemaId + dateAppend;
	}

	public void setRow(int row) {
		this.Row = row;
	}

	public void setCol(int col) {
		this.Col = col;
	}

	public void setShowTime(int showTime) {
		this.showTime = showTime;
	}

	// other useful functions
	public void dumpInfo() {
		System.out.println("--------------------Booking Info--------------------");
		System.out.println("Name: " + this.getName());
		System.out.println("Email: " + this.getEmail());
		System.out.println("Movie: " + this.getMovie());
		System.out.println("Cineplex: " + this.getCineplex());
		System.out.println("Showtime: " + this.getShowTime());
		System.out.printf("Booking seat: row %d col %d\n", this.getRow(), this.getCol());
		System.out.printf("Movie Type: %s\n", this.getMovieType().toString());
		System.out.printf("Cinema Type: %s\n", this.getCinemaType().toString());
		System.out.printf("Booking price: %f\n", this.getPrice());
	}

}