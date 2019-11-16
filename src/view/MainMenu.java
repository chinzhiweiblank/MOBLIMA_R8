package view;

import commons.OverallStateManager;

public class MainMenu extends View {

	boolean exit;
	private static OverallStateManager overallStateManager;

	public static void main(String[] args) {

		// load initial configurations
		overallStateManager = OverallStateManager.getInstance();
		overallStateManager.deserialize();

		new MainMenu().runMenu();
	}

	@Override
	protected int options() {
		System.out.println("1) Movies!");
		System.out.println("2) Admin Login");
		System.out.println("3) Exit");
		return 3; // return no. of options
	}

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
					System.out.println("Unknown error occurred");
			}
		}
		//System.out.println("Exiting program");
	}

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
