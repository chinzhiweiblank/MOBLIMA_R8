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

public class ConfigurationStateManager implements java.io.Serializable {

	private static ConfigurationStateManager singleton_instance = null;
	private ArrayList<String> holiday;
	private int maxChildAge;
	private int maxAdultAge;

	// singleton initialisation
	private ConfigurationStateManager() {
		this.holiday = new ArrayList<String>();
		this.maxChildAge = 12;
		this.maxAdultAge = 65;
	}

	public static ConfigurationStateManager getInstance() {
		if (singleton_instance == null) {

			// Synchronised ensures that only one thread is able to create the singleton
			synchronized (ConfigurationStateManager.class) {
				singleton_instance = new ConfigurationStateManager();
			}
		}
		return singleton_instance;
	}

	// helper methods
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

	public String formatDate(String Year, String Month, String day) {
		// date in YYYYMMDD format
		return Year + Month + day;
	}

	public void insertHoliday(String holidayDate) {
		this.holiday.add(holidayDate);
	}

	public void removeHoliday(String holidayDate) {
		this.holiday.remove(holidayDate);
	}

//	public int getTodayDateType() {
//		// return either weekday, weekend or holiday (1,2,3)
//		Calendar calendar = Calendar.getInstance();
//		DateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
//		String dateToday = dateFormat.format(calendar.getTime());
//		if (holiday.contains(dateToday)) {
//			// holiday
//			return 3;
//		} else {
//			if (getWeekend()) {
//				// weekend
//				return 2;
//			} else {
//				// weekday
//				return 1;
//			}
//		}
//	}

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


	public boolean verifyDate(String date){
		if (date.matches("[0-9]+") && date.length() == 8) {
			return true;
		}
		return false;
	}

	// getter setter
	public int getMaxChildAge() {
		return this.maxChildAge;
	}

	public int getMaxAdultAge() {
		return this.maxAdultAge;
	}

	public void setMaxChildAge(int age) {
		this.maxChildAge = age;
	}

	public void setMaxAdultAge(int age) {
		this.maxAdultAge = age;
	}

	// serializing and loading
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
