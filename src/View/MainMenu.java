package View;

import Controller.OverallStateManager;

/**
 * Overall View Object
 * Main menu for both admin and moviegoer
 */
public class MainMenu extends View {

	/**
	 * Condition for Exiting the Main Menu
	 */
	boolean exit;
	/**
	 * OverallStateManager to access the other Controller Classes
	 */
	private static OverallStateManager overallStateManager;

	/**
	 * Main function to run Main Menu
	 * @param args a list of strings as arguments
	 */
	public static void main(String[] args) {


		// load initial configurations
		overallStateManager = OverallStateManager.getInstance();
		overallStateManager.deserialize();

		new MainMenu().runMenu();
	}

	/**
	 * Prints out available options
	 * @return Number of options available
	 */
	@Override
	protected int options() {

		System.out.println("1) Movies!");
		System.out.println("2) Admin Login");
		System.out.println("3) Exit");
		return 3; // return no. of options
	}

	/**
	 * Print choices for admin or moviegoer to select
	 */
	@Override
	protected void runMenu() {

		printHeader();
		while (!exit) {
			int choice = getInput(options());
			switch (choice) {
				case 1:
					display(this, new MovieGoerMenu());
					break;
				case 2:
					display(this, new AdminMenu());
					break;
				case 3:
					overallStateManager.serialize();
					exit = true;
					System.out.println("Exiting Program");
					System.exit(0);
				case 4:
					System.out.println("Please Try Again");
				default:
					System.out.println("Please input a valid integer choice");
			}
		}
		//System.out.println("Exiting program");
	}

	/**
	 * Prints frontend header for MOBLIMA
	 */
	private void printHeader() {

//		System.out.println(" __      __   _                    _           ");
//		System.out.println(" \\ \\    / /__| |__ ___ _ __  ___  | |_ ___     ");
//		System.out.println("  \\ \\/\\/ / -_) / _/ _ \\ '  \\/ -_) |  _/ _ \\    ");
//		System.out.println("   \\_/\\_/\\___|_\\__\\___/_|_|_\\___|  \\__\\___/");
		System.out.println("+--------------------------------------------------------+");
		System.out.println("|                       WELCOME TO                       |");
		System.out.println("|    __  __  ____  ____  _      _____ __  __             |");
		System.out.println("|	|  \\/  |/ __ \\|  _ \\| |    |_   _|  \\/  |   /\\       |");
		System.out.println("|   | \\  / | |  | | |_) | |      | | | \\  / |  /  \\      |");
		System.out.println("|   | |\\/| | |  | |  _ <| |      | | | |\\/| | / /\\ \\     |");
		System.out.println("|   | |  | | |__| | |_) | |____ _| |_| |  | |/ ____ \\    |");
		System.out.println("|   |_|  |_|\\____/|____/|______|_____|_|  |_/_/    \\_\\   |");
		System.out.println("+--------------------------------------------------------+");


	}

}
