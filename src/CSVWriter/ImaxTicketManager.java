package CSVWriter;

import java.io.IOException;

public class ImaxTicketManager implements TicketManager {
	private int movieType = 3;

	public double readPrice(int movieClass, int age, int day) throws IOException {
		return CSVcontroller.CSVstatic.readPrice(movieType, movieClass, age, day);
	}

	public int updatePrice(int movieClass, int age, int day, int price) throws IOException {
		return CSVcontroller.CSVstatic.updatePrice(movieType, movieClass, age, day, price);
	}
}
