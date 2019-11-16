package CSVWriter;

import java.io.IOException;

/**
 * Controllers Class for IMAX movie tickets
 */
public class ImaxTicketManager implements TicketManager {
	/**
	 * Type of Movie for IMAX is 3
	 */
	private int movieType = 3;

	/**
	 * Reads the price
	 * @param movieClass Class of movie
	 * @param age Age of person
	 * @param day Day when ticket was booked
	 * @return Ticket Price
	 */
	public double readPrice(int movieClass, int age, int day) throws IOException {

		return CSVcontroller.CSVstatic.readPrice(movieType, movieClass, age, day);
	}

	/**
	 * Update the price
	 * @param movieClass Class of movie booked
	 * @param age Age of person booking
	 * @param day Day when ticket was booked
	 * @param price Price of Ticket
	 * @return 1 if successful and 0 otherwise
	 */
	public int updatePrice(int movieClass, int age, int day, int price) throws IOException {
		return CSVcontroller.CSVstatic.updatePrice(movieType, movieClass, age, day, price);
	}
}
