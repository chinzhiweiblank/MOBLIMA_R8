package CSVWriter;

import java.io.IOException;

public interface TicketManager {
	public double readPrice(int movieClass, int age, int day) throws IOException;

	public int updatePrice(int movieClass, int age, int day, int price) throws IOException;
}
