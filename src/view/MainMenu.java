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
					System.out.println("Please input a valid integer choice");
			}
		}
		//System.out.println("Exiting program");
	}

	private void printHeader() {
		System.out.println("+-----------------------------+");
		System.out.println("|     WELCOME TO MOBLIMA      |");
		System.out.println("+-----------------------------+");

	}

}
