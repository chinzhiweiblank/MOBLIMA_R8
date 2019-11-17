package Model;

import java.io.IOException;

/**
 * Ticket manager interface for others ticket managers to implement
 */
public interface TicketManager {
	/**
	 * Reads the price
	 * @param movieClass Class of Movie booked
	 * @param age Age of Person booking
	 * @param day Day when Ticket was booked
	 * @return Ticket Price
	 * @throws IOException if input is wrong
	 */
	public double readPrice(int movieClass, int age, int day) throws IOException;

	/**
	 * Reads the price
	 * @param movieClass Class of Movie booked
	 * @param age Age of Person booking
	 * @param day Day when Ticket was booked
	 * @param price Price of Ticket booked
	 * @return Ticket Price
	 * @throws IOException if input is wrong
	 */
	public int updatePrice(int movieClass, int age, int day, int price) throws IOException;
}
