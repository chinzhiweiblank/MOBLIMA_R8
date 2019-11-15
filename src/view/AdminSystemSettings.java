package view;

import java.util.Scanner;

import commons.ConfigurationStateManager;

public class AdminSystemSettings extends View {

	@Override
	protected int options() {
		System.out.println("----------------------------");
		System.out.println("1) Add holiday date");
		System.out.println("2) Remove holiday date");
		System.out.println("3) previous menu");
		return 3;
	}

	@Override
	protected void runMenu() {
		ConfigurationStateManager configurationStateManager = ConfigurationStateManager.getInstance();
		int choice = getInput(options());
		switch (choice) {
		case 1:
			addHolidayDates(configurationStateManager);
			System.out.println("holiday date added");
			break;
		case 2:
			removeHolidayDates(configurationStateManager);
			System.out.println("holiday date removed");
			break;
		case 3:
			getPrevView();
			break;
		default:
			System.out.println("Unknown error occured");
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

	private void removeHolidayDates(ConfigurationStateManager configurationStateManager) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Holiday Year: ");
		String year = sc.nextLine();

		System.out.println("Enter Holiday month: ");
		String month = sc.nextLine();

		System.out.println("Enter Holiday day: ");
		String day = sc.nextLine();

		String holidayDate = configurationStateManager.formatDate(year, month, day);
		configurationStateManager.removeHoliday(holidayDate);
	}
}
