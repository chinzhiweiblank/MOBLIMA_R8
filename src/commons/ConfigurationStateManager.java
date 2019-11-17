package commons;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Controller Class to manage system configuration settings
 */
public class ConfigurationStateManager implements java.io.Serializable {

	/**
	 * To ensure that the ConfigurationStateManager is a singleton
	 */
	private static ConfigurationStateManager singleton_instance = null;
	/**
	 * A list of holidays
	 */
	private ArrayList<String> holiday;
	/**
	 * The maximum age by which a person is considered a child
	 */
	private int maxChildAge;
	/**
	 * The maximum age by which a person is considered an adult
	 */
	private int maxAdultAge;

	/**
	 * Initialization of the ConfigurationStateManager singleton
	 */
	private ConfigurationStateManager() {
		this.holiday = new ArrayList<String>();
		this.maxChildAge = 12;
		this.maxAdultAge = 65;
	}

	/**
	 * Obtains an instance of the ConfigurationStateManager
	 * @return an instance of the ConfigurationStateManager
	 */
	public static ConfigurationStateManager getInstance() {
		if (singleton_instance == null) {

			// Synchronised ensures that only one thread is able to create the singleton
			synchronized (ConfigurationStateManager.class) {
				singleton_instance = new ConfigurationStateManager();
			}
		}
		return singleton_instance;
	}

	/**
	 * Checks whether the day is a weekend
	 * @return true if the day is a weekend and false otherwise
	 */
	private boolean getWeekend() {
		// sunday, mon, tues, wed ... saturday
		// 1,2,3,4,...7
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (day == 1 || day == 7) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Obtains the day in a YYYYMMDD format
	 * @param Year year
	 * @param Month month
	 * @param day day
	 * @return the day in a YYYYMMDD format
	 */
	public String formatDate(String Year, String Month, String day) {
		// date in YYYYMMDD format
		return Year + Month + day;
	}

	/**
	 * Inserts a holiday into the list of holidays
	 * @param holidayDate the holiday to be inserted
	 */
	public void insertHoliday(String holidayDate) {
		this.holiday.add(holidayDate);
	}

	/**
	 * Removes a holiday from the list of holidays
	 * @param holidayDate the date of the holiday to be removed
	 * @return 1 if removal is successful or 0 otherwise
	 */
	public int removeHoliday(String holidayDate) {
		if (this.holiday.contains(holidayDate)){
			this.holiday.remove(holidayDate);
			return 1;
		}
		return 0;
	}


	/**
	 * Checks a date is a holiday or a weekend or a weekday
	 * @param date the date to be checked
	 * @return 3 if it is a holiday, 2 if it is weekend, 1 if it is a weekday
	 */
	public int getTodayDateType(String date) {
		// return either weekday, weekend or holiday (1,2,3)
		if (holiday.contains(date)) {
			// holiday
			return 3;
		} else {
			if (getWeekend()) {
				// weekend
				return 2;
			} else {
				// weekday
				return 1;
			}
		}
	}

	/**
	 * Obtains the age bracket the user of a specified age belongs in
	 * @param age age of the user
	 * @return 1 if the user is a child, 2 for an adult, 3 for a senior
	 */
	public int getAgeType(int age) {
		// return 1,2,3 for child,adult,senior
		if (age < maxChildAge) {
			return 1;
		} else if (age < maxAdultAge) {
			return 2;
		} else {
			return 3;
		}
	}


	/**
	 * Verifies whether a date is in the correct format
	 * @param date date to be verified
	 * @return true if the date is in the correct format or false otherwise
	 */
	public boolean verifyDate(String date){
		if (date.matches("[0-9]+") && date.length() == 8) {
			return true;
		}
		return false;
	}

	/**
	 * Obtains the maximum age a person could be considered a child
	 * @return the maximum age a person could be considered a child
	 */
	public int getMaxChildAge() {
		return this.maxChildAge;
	}

	/**
	 * Obtains the maximum age a person could be considered an adult
	 * @return the maximum age a person could be considered an adult
	 */
	public int getMaxAdultAge() {
		return this.maxAdultAge;
	}

	/**
	 * Sets the maximum age a person could be considered a child
	 * @param age the maximum age a person could be considered a child
	 */
	public void setMaxChildAge(int age) {
		this.maxChildAge = age;
	}

	/**
	 * Sets the maximum age a person could be considered an adult
	 * @param age the maximum age a person could be considered an adult
	 */
	public void setMaxAdultAge(int age) {
		this.maxAdultAge = age;
	}


	public ArrayList<String> getHolidayDate(){
		return this.holiday;
	}

	/**
	 * Creates a byte stream of data from the state of an object
	 */
	public void serialize() {

		try {
			String filename = "data/ConfigurationState.ser";
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			// Method for serialization of object
			out.writeObject(this);

			out.close();
			file.close();

		}

		catch (IOException exceptionMessage) {
			System.out.println(exceptionMessage);
		}
	};

	/**
	 * Writes a byte stream of data into the state of an object
	 */
	public void deserialize() {
		// Deserialization
		try {
			// Reading the object from a file
			String filename = "data/ConfigurationState.ser";
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);

			// Method for deserialization of object
			ConfigurationStateManager deserializeObj = (ConfigurationStateManager) in.readObject();

			this.holiday = deserializeObj.holiday;
			this.maxChildAge = deserializeObj.maxChildAge;
			this.maxAdultAge = deserializeObj.maxAdultAge;

			in.close();
			file.close();

		}

		catch (IOException exceptionMessage) {
			System.out.println(exceptionMessage);
		}

		catch (ClassNotFoundException exceptionMessage) {
			System.out.println("ClassNotFoundException is caught");
		}
	}
}
