package CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVcontroller {

	public static class CSVstatic {
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

		private static String getFilePath(int movieType) {
			String filePath = new File("").getAbsolutePath() + "/data/";

			// Generate file path that links to correct CSV Database of Prices
			List<String> extension = Arrays.asList("blockbuster.csv", "3d.csv", "imax.csv");
			filePath += extension.get(movieType - 1);
			return filePath;
		}

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
