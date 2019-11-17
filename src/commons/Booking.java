package commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import movie.Movie.MovieType;

/**
 * Represents a booking the moviegoer is making or has made
 */
public class Booking implements java.io.Serializable {
	/**
	 *  Row of Seat booked
	 */
	private int Row;
	/**
	 *  Column of Seat booked
	 */
	private int Col;
	/**
	 * Time for the movie session booked
	 */
	private int showTime;
	/**
	 * Price of the movie ticket booked
	 */
	private double price;
	/**
	 * The mobile number of the user who booked the ticket
	 */
	private String mobileNumber;
	/**
	 * ID of the cinema booked
	 */
	private String CinemaId;
	/**
	 * ID of the transaction of this booking
	 */
	private String TID;
	/**
	 * Title of the movie booked
	 */
	private String Movie;
	/**
	 * Name of the Cineplex booked
	 */
	private String Cineplex;
	/**
	 * Email of the user who booked the ticket
	 */
	private String email;
	/**
	 * Name of the user who booked the ticket
	 */
	private String name;
	/**
	 * Type of movie booked
	 */
	private MovieType movieType;
	/**
	 * Type of cinema booked
	 */
	private Cinema.CinemaType cinemaType;
	private String date;

	/**
	 * Constructor for the Booking
	 * @param Movie Title of the movie booked
	 * @param CinemaId ID of the cinema booked
	 * @param Cineplex Name of the Cineplex booked
	 * @param Row Row of the seat booked
	 * @param Col Column of the seat booked
	 * @param showTime Time of the movie booked
	 * @param email Email of the user who booked the ticket
	 * @param name Name of the user who booked the ticket
	 * @param mobileNumber Mobile Number of the user who booked the ticket
	 * @param price Price of the ticket
	 * @param movieType Type of Movie booked
	 * @param cinemaType Type of Cinema booked
	 * @param date Date when Cinema was booked
	 */
	public Booking(String Movie, String CinemaId, String Cineplex, int Row, int Col, int showTime, String email,
				   String name, String mobileNumber, double price, MovieType movieType, Cinema.CinemaType cinemaType, String date) {
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
		this.date = date;
	}

	/**
	 * Obtains the title of the movie booked
	 * @return the title of the movie
	 */
	public String getMovie() {
		return this.Movie;
	}

	/**
	 * Obtains the name of the cineplex
	 * @return the name of the cineplex
	 */
	public String getCineplex() {
		return this.Cineplex;
	}

	/**
	 * Obtains the ID of the transaction of this booking
	 * @return the ID of the booking transaction
	 */
	public String getTID() {
		return this.TID;
	}

	/**
	 * Obtains the row of the seat booked
	 * @return the row of the seat booked
	 */
	public int getRow() {
		return this.Row;
	}

	/**
	 * Obtains the column of the seat booked
	 * @return the column of the seat booked
	 */
	public int getCol() {
		return this.Col;
	}

	/**
	 * Obtains the id of the cinema booked
	 * @return the id of the cinema booked
	 */
	public String getCinemaId() {
		return this.CinemaId;
	}

	/**
	 * Obtains the time of the movie booked
	 * @return the time of the movie booked
	 */
	public int getShowTime() {
		return this.showTime;
	}

	/**
	 * Obtains the mobile number of the user who made the booking
	 * @return the mobile number of the user who made the booking
	 */
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	/**
	 * Obtains the email of the user who made the booking
	 * @return the email of the user who made the booking
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Obtains the name of the user who made this booking
	 * @return the name of the user who made this booking
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Obtains the price of the ticket
	 * @return the price of the ticket
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Obtains the type of movie booked
	 * @return the type of movie booked
	 */
	public MovieType getMovieType() {
		return this.movieType;
	}

	/**
	 * Obtains the type of cinema booked
	 * @return the type of cinema booked
	 */
	public Cinema.CinemaType getCinemaType(){return this.cinemaType;}

	/**
	 * Sets the title of the movie booked
	 * @param movie the title of the movie booked
	 */
	public void setMovie(String movie) {
		this.Movie = movie;
	}

	/**
	 * Sets the ID of the transaction which made this booking
	 */
	public void initTID() {
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmm");
		String dateAppend = dateFormat.format(calendar.getTime());
		this.TID = this.CinemaId + dateAppend;
	}

	/**
	 * Sets the row of the seat booked
	 * @param row the row of the seat booked
	 */
	public void setRow(int row) {
		this.Row = row;
	}

	/**
	 * Sets the column of the seat booked
	 * @param col the column of the seat booked
	 */
	public void setCol(int col) {
		this.Col = col;
	}

	/**
	 * Sets the time the movie is booked at
	 * @param showTime the time the movie is booked at
	 */
	public void setShowTime(int showTime) {
		this.showTime = showTime;
	}

	public String getDate() {return this.date;}

	/**
	 * Prints out the information about the Booking
	 */
	public void dumpInfo() {
		System.out.println("--------------------Booking Info--------------------");
		System.out.println("Name: " + this.getName());
		System.out.println("Email: " + this.getEmail());
		System.out.println("Movie: " + this.getMovie());
		System.out.println("Cineplex: " + this.getCineplex());
		System.out.println("Show date: " + this.getDate());
		System.out.println("Showtime: " + this.getShowTime());
		System.out.printf("Booking seat: row %d col %d\n", this.getRow(), this.getCol());
		System.out.printf("Movie Type: %s\n", this.getMovieType().toString());
		System.out.printf("Cinema Type: %s\n", this.getCinemaType().toString());
		System.out.printf("Booking price: %.2f\n", this.getPrice());
	}

}