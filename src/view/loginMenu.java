package view;

import java.util.Scanner;

import commons.AccountStateManager;
import commons.AccountType;
import commons.OverallStateManager;
import commons.Person;

public class loginMenu extends View {

	private AccountStateManager accountStateManager = AccountStateManager.getInstance();
	private OverallStateManager overallStateManager = OverallStateManager.getInstance();
	private AccountType accountType;

	public loginMenu(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	protected int options() {
		System.out.println("+--------------------------------------------------------+");
		System.out.println("1) Login!");
		System.out.println("2) previous menu");
		return 2;
	}

	@Override
	protected void runMenu() {
		Scanner sc = new Scanner(System.in);
		boolean loginSuccess = false;
		loop: while (!loginSuccess) {
			int choice = getInput(options());
			switch (choice) {
			case 1:
				loginSuccess = login(sc);
				// break out of while loop
				if (loginSuccess) {
					System.out.println("Login successful!");
					break loop;
				} else {
					display(this, new loginMenu(AccountType.ADMIN));
					break;
				}
			case 2:
				display(this, new MainMenu());
				break;
			case -1:
				break;
			default:
				System.out.println("Unknown error occurred");
			}
		}
		// go back to previous page
		//getPrevView();
	}

	private boolean login(Scanner sc) {
		// ask for inputs
		System.out.println("Login Menu");
		System.out.println("-----------------------");
		System.out.println("Username:");
		String userName = sc.next();
		System.out.println("Password:");
		String password = sc.next();

		if (accountStateManager.verifyAccount(userName, password, this.accountType)) {
			Person account = accountStateManager.readAccount(userName, this.accountType);
			overallStateManager.setCurrentUserAccount(account, this.accountType);
			return true;
		} else {
			System.out.println("Verification of account failed");
			return false;
		}
	}
}
