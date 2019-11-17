package view;

import Controller.AccountStateManager;
import Model.AccountType;
import Controller.OverallStateManager;
/**
 * View class for admin
 */
public class AdminMenu extends View {
	boolean authorised;
	private AccountStateManager accountStateManager = AccountStateManager.getInstance();
	private OverallStateManager overallStateManager = OverallStateManager.getInstance();

	/**
	 * Prints out available options
	 * @return Number of options available
	 */
	@Override
	protected int options() {

		System.out.println("+--------------------------------------------------------+");
		System.out.println("1) CRUD movie listing");
		System.out.println("2) CRUD cinema showtimes and movies showing");
		System.out.println("3) Configure system settings");
		System.out.println("4) Return to main");
		return 4;
	}

	/**
	 * Runs the function which the admin selects
	 */
	@Override
	protected void runMenu() {

		if (!overallStateManager.getloginStatus(AccountType.ADMIN)) {
			login();
		}
		int choice = getInput(options());
		switch (choice) {
			case 1:
				display(this, new AdminMovieListing());
				break;
			case 2:
				display(this, new AdminMovieShowtime());
				break;
			case 3:
				display(this, new AdminSystemSettings());
				break;
			case 4:
				getPrevView();
				break;
			default:
				System.out.println("Please input a valid integer choice");

		}

	}

	/**
	 * Function to authenticate admin user
	 */
	private void login() {
		display(this, new loginMenu(AccountType.ADMIN));
	}

}
