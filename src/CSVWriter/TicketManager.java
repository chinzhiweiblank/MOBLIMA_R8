package CSVWriter;

import java.io.IOException;

/**
 * ticket manager interface for others ticket managers to implement
 */
public interface TicketManager {
	public double readPrice(int movieClass, int age, int day) throws IOException;

	public int updatePrice(int movieClass, int age, int day, int price) throws IOException;
}
