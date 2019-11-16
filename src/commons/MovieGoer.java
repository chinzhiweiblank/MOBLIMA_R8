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
	 * @param firstName
	 * @param lastName
	 * @param Bookings
	 * @param mobile
	 * @param email
	 * @param password
	 * @param reviews
	 * @param age
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

	// getters
	public ArrayList<Booking> getBookings() {
		return Bookings;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return this.password;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public int getAge() {
		return this.age;
	}

	// setters
	public void setBookings(ArrayList<Booking> newBookings) {
		this.Bookings = newBookings;
	}

	public void setMobile(String newMobile) {
		this.mobile = newMobile;
	}

	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	public void setReviews(ArrayList<Review> newReviews) {
		this.reviews = newReviews;
	}

	public void insertReview(Review newReview) {
		this.reviews.add(newReview);
	}

	public void insertBooking(Booking newBooking) {
		this.Bookings.add(newBooking);
	}

	// verify login
	public boolean verifyLogin(String inputUsername, String inputPassword) {
		if (this.email.equals(inputUsername) && this.password.equals(inputPassword)) {
			return true;
		} else {
			return false;
		}
	}

}
