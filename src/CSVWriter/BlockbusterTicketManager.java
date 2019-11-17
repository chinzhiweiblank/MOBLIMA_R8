package CSVWriter;

import java.io.IOException;

/**
 * Controller for BlockBuster Movie Tickets
 */
public class BlockbusterTicketManager implements TicketManager {
	/**
	 * Type of movie for Blockbuster is 1
	 */
	private int movieType = 1;

	/**
	 * Constructor for BlockbusterTicketManager
	 */
	public BlockbusterTicketManager(){};
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
