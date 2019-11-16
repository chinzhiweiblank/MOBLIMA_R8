package CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller class for CSV Files
 */
public class CSVcontroller {

	/**
	 * Constructor for CSVcontroller
	 */
	public CSVcontroller(){};
	/**
	 * Static class for Csv Controller
	 */
	public static class CSVstatic {
		/**
		 * load database
		 * @param movieType type of movie
		 * @return a list of list of strings representing the database
		 */
		private static List<List<String>> loadDatabase(int movieType) throws IOException {
			String row;
			List<List<String>> database = new ArrayList<List<String>>();

			// Get File Path of Movie Type
			String filePath = getFilePath(movieType);

			// Load the values of rows from CSV
			BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				database.add(Arrays.asList(data));
			}
			csvReader.close();
			return database;
		}

		/**
		 * Gets the file path based on the type of movie
		 * @param movieType Type of movie
		 * @return filepath The path of the file
		 */
		private static String getFilePath(int movieType) {
			/**
			 * get the file path
			 * @param int movieType
			 * @return String filepath
			 */
			String filePath = new File("").getAbsolutePath() + "/data/";

			// Generate file path that links to correct CSV Database of Prices
			List<String> extension = Arrays.asList("blockbuster.csv", "3d.csv", "imax.csv");
			filePath += extension.get(movieType - 1);
			return filePath;
		}

		/**
		 * Reads the price
		 * @param movieType Type of Movie booked
		 * @param movieClass Class of Movie booked
		 * @param age Age of Person booking
		 * @param day Day when Ticket was booked
		 * @return Ticket Price
		 * @throws IOException Exception raised when input is wrong
		 */
		protected static double readPrice(int movieType, int movieClass, int age, int day) throws IOException {
			List<List<String>> database = new ArrayList<List<String>>();

			// Error checking for invalid input
			if (movieType <= 0 || movieClass <= 0 || age <= 0 || day <= 0) {
				return -1.0;
			}

			// Load Database from CSV File
			database = loadDatabase(movieType);

			// Return the price of ticket
			return Double.parseDouble(database.get(age + 4 * (movieClass - 1)).get(day));
		}

		/**
		 * Updates the price
		 * @param movieType Type of Movie booked
		 * @param movieClass Class of Movie booked
		 * @param age Age of user booking
		 * @param day Day of ticket booked
		 * @param price Price of ticket
		 * @return Updated price
		 * @throws IOException Exception thrown when Input is wrong
		 */
		protected static int updatePrice(int movieType, int movieClass, int age, int day, int price)
				throws IOException {
			List<List<String>> database = new ArrayList<List<String>>();

			// Error checking for invalid input
			if (movieType <= 0 || movieClass <= 0 || age <= 0 || day <= 0) {
				return 1;
			}

			// Load Database from CSV File
			database = loadDatabase(movieType);

			// Initialize filePath of CSV File to Update
			String filePath = getFilePath(movieType);

			// Update specific value of movie price
			database.get(age + 4 * (movieClass - 1)).set(day, Integer.toString(price));

			// Write to CSV File
			FileWriter csvWriter = new FileWriter(filePath);
			for (List<String> rowData : database) {
				csvWriter.append(String.join(",", rowData));
				csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();

			return 0;
		}
	}
}
