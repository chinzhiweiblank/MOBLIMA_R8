package CSVWriter;

import java.io.IOException;

/**
 * IMAX Movie Ticket Manager class
 */
public class ImaxTicketManager implements TicketManager {
	private int movieType = 3;

	public double readPrice(int movieClass, int age, int day) throws IOException {
		/**
		 * read the price
		 * @param int movieClass
		 * @param int age
		 * @param int day
		 * @return double price
		 */
		return CSVcontroller.CSVstatic.readPrice(movieType, movieClass, age, day);
	}

	public int updatePrice(int movieClass, int age, int day, int price) throws IOException {
		/**
		 * update the price
		 * @param int movieClass
		 * @param int age
		 * @param int day
		 * @param int price
		 * @return int updated price
		 */
		return CSVcontroller.CSVstatic.updatePrice(movieType, movieClass, age, day, price);
	}
}
