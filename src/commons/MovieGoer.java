package commons;

import java.util.ArrayList;

import movie.Review;

/**
 * Model class representing a moviegover
 */
public class MovieGoer extends Person {
	/**
	 * List of bookings made by the moviegoer
	 */
	private ArrayList<Booking> Bookings;
	/**
	 * List of reviews made by the moviegoer
	 */
	private ArrayList<Review> reviews;
	/**
	 * Mobile number of the moviegoer
	 */
	private String mobile;
	/**
	 * Email Address of the moviegoer
	 */
	private String email;
	/**
	 * Password of the moviegoer
	 */
	private String password;
	/**
	 * Age of the moviegoer
	 */
	private int age;

	/**
	 * Constructor for the MovieGoer class
	 * @param firstName First name of the moviegoer
	 * @param lastName last name of the moviegoer
	 * @param Bookings List of the bookings made by the moviegoer
	 * @param mobile Mobile Number of the moviegoer
	 * @param email Email Address of the moviegoer
	 * @param password Password of the moviegoer
	 * @param reviews List of reviews made by the moviegoer
	 * @param age Age of the moviegoer
	 */
	public MovieGoer(String firstName, String lastName, ArrayList<Booking> Bookings, String mobile, String email,
			String password, ArrayList<Review> reviews, int age) {
		super(firstName, lastName);
		this.Bookings = Bookings;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.reviews = reviews;
		this.age = age;
	}

	/**
	 * Obtains the list of bookings made by the moviegoer
	 * @return the list of bookings made by the moviegoer
	 */
	public ArrayList<Booking> getBookings() {
		return Bookings;
	}

	/**
	 * Obtains the mobile number of the moviegoer
	 * @return the mobile number of the moviegoer
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Obtains the email address of the moviegoer
	 * @return the email address of the moviegoer
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Obtains the password of the moviegoer
	 * @return the password of the moviegoer
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Obtains a list of reviews made by the moviegoer
	 * @return a list of reviews made by the moviegoer
	 */
	public ArrayList<Review> getReviews() {
		return reviews;
	}

	/**
	 * Obtains the age of the moviegoer
	 * @return the age of the moviegoer
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Sets the list of bookings made by the moviegoer
	 * @param newBookings the list of bookings made by the moviegoer
	 */
	public void setBookings(ArrayList<Booking> newBookings) {
		this.Bookings = newBookings;
	}

	/**
	 * Sets the mobile number of the moviegoer
	 * @param newMobile the mobile number of the moviegoer
	 */
	public void setMobile(String newMobile) {
		this.mobile = newMobile;
	}

	/**
	 * Sets the email address of the moviegoer
	 * @param newEmail the email address of the moviegoer
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	/**
	 * Sets the list of reviews made by the moviegoer
	 * @param newReviews the list of reviews made by the moviegoer
	 */
	public void setReviews(ArrayList<Review> newReviews) {
		this.reviews = newReviews;
	}

	/**
	 * Inserts a new review into the list of reviews
	 * @param newReview a new review
	 */
	public void insertReview(Review newReview) {
		this.reviews.add(newReview);
	}

	/**
	 * Inserts a new booking into the list of bookings
	 * @param newBooking a new booking
	 */
	public void insertBooking(Booking newBooking) {
		this.Bookings.add(newBooking);
	}

	/**
	 * Verifies whether a login is successful with the username and password
	 * @param inputUsername the username of the moviegoer
	 * @param inputPassword the password of the moviegoer
	 * @return true if login is successful and false otherwise
	 */
	public boolean verifyLogin(String inputUsername, String inputPassword) {
		if (this.email.equals(inputUsername) && this.password.equals(inputPassword)) {
			return true;
		} else {
			return false;
		}
	}
}
