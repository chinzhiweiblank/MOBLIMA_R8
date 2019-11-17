package Model;

import java.io.IOException;

/**
 * 3D Movie Ticket Manager class
 */
public class Movie3DTicketManager implements TicketManager {
	/**
	 * Type of movie for 3D is 2
	 */
	private int movieType = 2;

	/**
	 * Constructor for Movie3DTicketManager
	 */
	public Movie3DTicketManager(){};
	/**
	 * Reads the price
	 * @param movieClass Class of Movie booked
	 * @param age Age of Person booking
	 * @param day Day when Ticket was booked
	 * @return Ticket Price
	 */
	public double readPrice(int movieClass, int age, int day) throws IOException {
		return CSVcontroller.CSVstatic.readPrice(movieType, movieClass, age, day);
	}

	/**
	 * Updates the price
	 * @param movieClass Class of Movie booked
	 * @param age Age of Person booking
	 * @param day Day when Ticket was Booked
	 * @param price Price of Ticket
	 * @return 1 if Successful and 0 otherwise
	 */
	public int updatePrice(int movieClass, int age, int day, int price) throws IOException {
		return CSVcontroller.CSVstatic.updatePrice(movieType, movieClass, age, day, price);
	}
}
