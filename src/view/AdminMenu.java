package view;

import commons.AccountStateManager;
import commons.AccountType;
import commons.OverallStateManager;
/**
 * main menu for admin
 */
public class AdminMenu extends View {
	boolean authorised;
	private AccountStateManager accountStateManager = AccountStateManager.getInstance();
	private OverallStateManager overallStateManager = OverallStateManager.getInstance();

	@Override
	/**
	 * print the various options for admin to select
	 */
	protected int options() {

		System.out.println("+--------------------------------------------------------+");
		System.out.println("1) CRUD movie listing");
		System.out.println("2) CRUD cinema showtimes and movies showing");
		System.out.println("3) Configure system settings");
		System.out.println("4) Return to main");
		return 4;
	}

	@Override
	/**
	 * run the function which the admin selects
	 */
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
	 * function to authenticate admin user
	 */
	private void login() {

		display(this, new loginMenu(AccountType.ADMIN));
	}

}
