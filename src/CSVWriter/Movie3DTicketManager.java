package CSVWriter;

import java.io.IOException;

public class Movie3DTicketManager implements TicketManager {
	private int movieType = 2;

	public double readPrice(int movieClass, int age, int day) throws IOException {
		return CSVcontroller.CSVstatic.readPrice(movieType, movieClass, age, day);
	}

	public int updatePrice(int movieClass, int age, int day, int price) throws IOException {
		return CSVcontroller.CSVstatic.updatePrice(movieType, movieClass, age, day, price);
	}
}
