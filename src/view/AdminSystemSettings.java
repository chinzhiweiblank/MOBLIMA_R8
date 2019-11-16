package view;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import commons.ConfigurationStateManager;
/**
 * View Class for admin to edit holiday dates
 */
public class AdminSystemSettings extends View {

	/**
	 * Constructor for AdminSystemSettings
	 */
	public AdminSystemSettings(){}
	/**
	 * print the various options for admin to select
	 */
	@Override
	protected int options() {

		System.out.println("+--------------------------------------------------------+");
		System.out.println("1) Add holiday date");
		System.out.println("2) Remove holiday date");
		System.out.println("3) View holiday date");
		System.out.println("4) Previous menu");
		return 4;
	}

	/**
	 * choices for admin to edit holiday dates
	 */
	@Override
	protected void runMenu() {

		boolean end = true;
		ConfigurationStateManager configurationStateManager = ConfigurationStateManager.getInstance();
		while(end){
			int choice = getInput(options());
			switch (choice) {
				case 1:
					addHolidayDates(configurationStateManager);
					System.out.println("Holiday date added");
					break;
				case 2:
					int success = removeHolidayDates(configurationStateManager);
					if (success == 1){
						System.out.println("Holiday date removed");
					} else {
						System.out.println("Holiday date not in stored dates");
					}
					break;
				case 3:
					viewHolidayDates(configurationStateManager);
					break;
				case 4:
					getPrevView();
					end = false;
					break;
				default:
					System.out.println("Please input a valid integer choice");
			}
		}
	}

	/**
	 * add holiday dates to the system
	 * @param configurationStateManager configuration state manager
	 */
	private void addHolidayDates(ConfigurationStateManager configurationStateManager) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Holiday Year: ");
		String year = sc.nextLine();

		System.out.println("Enter Holiday month: ");
		String month = sc.nextLine();

		System.out.println("Enter Holiday day: ");
		String day = sc.nextLine();

		String holidayDate = configurationStateManager.formatDate(year, month, day);
		configurationStateManager.insertHoliday(holidayDate);
	}

	/**
	 * remove holiday dates to the system
	 * @param configurationStateManager configuration state manager
	 * @return 1 if successfully removed holiday, 0 if unsuccessful
	 */
	private int removeHolidayDates(ConfigurationStateManager configurationStateManager) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Holiday Year: ");
		String year = sc.nextLine();

		System.out.println("Enter Holiday month: ");
		String month = sc.nextLine();

		System.out.println("Enter Holiday day: ");
		String day = sc.nextLine();

		String holidayDate = configurationStateManager.formatDate(year, month, day);
		int result = configurationStateManager.removeHoliday(holidayDate);
		return result;
	}


	/**
	 * view holiday dates
	 * @param configurationStateManager configuration state manager
	 */
	private void viewHolidayDates(ConfigurationStateManager configurationStateManager){

		ArrayList<String> holidayDates = configurationStateManager.getHolidayDate();
		System.out.println("Holiday dates: ");

		if (holidayDates.size() == 0){
			System.out.println("No holiday dates inputted");
			return;
		}
		for (String holidayDate : holidayDates){
			System.out.println(holidayDate);
		}
	}
}
