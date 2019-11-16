package view;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import commons.ConfigurationStateManager;

public class AdminSystemSettings extends View {

	@Override
	protected int options() {
		System.out.println("----------------------------");
		System.out.println("1) Add holiday date");
		System.out.println("2) Remove holiday date");
		System.out.println("3) View holiday date");
		System.out.println("4) Previous menu");
		return 4;
	}

	@Override
	protected void runMenu() {
		ConfigurationStateManager configurationStateManager = ConfigurationStateManager.getInstance();
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
			viewHolidaDates(configurationStateManager);
			break;
		case 4:
			getPrevView();
			break;
		default:
			System.out.println("Please input a valid integer choice");
		}

	}

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


	private void viewHolidaDates(ConfigurationStateManager configurationStateManager){
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
