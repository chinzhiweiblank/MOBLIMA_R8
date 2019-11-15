package commons;

import java.util.ArrayList;

import movie.Review;

public class MovieGoer extends Person {
	private ArrayList<Booking> Bookings;
	private ArrayList<Review> reviews;
	private String mobile;
	private String email;
	private String password;
	private int age;

	// constructors
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
