package view;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Abstract menu which contains general methods
 */
public abstract class View {

	/**
	 * Previous Menu
	 */
	public View prevView;

	/**
	 * Displays options available
	 * @return Number of options available
	 */
	protected abstract int options(); // options in each menu

	/**
	 * Executes the user interface for the menu
	 */
	protected abstract void runMenu();

	/**
	 * request input from user
	 * @param Opts options
	 * @return int choice if successful, -1 if unsuccessful
	 */
	protected int getInput(int Opts) { // error checking for input

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter choice: ");
		try {
			int choice = sc.nextInt();
			while (choice < 0 || choice > Opts) {
				System.out.println("Input error. Please try again.");
				System.out.println("Enter choice: ");
				choice = sc.nextInt();
			}
			return choice;
		}
		catch(InputMismatchException inputE){
			System.out.println("Your input was not a number. Please try again.");
			return -1;
		}
	}

	/**
	 * Displays the view
	 * @param  v view v
	 * @param  u view u
	 */
	protected void display(View v, View u) {

		u.prevView = v;
		u.runMenu();
	}

	/**
	 * Gets previous view
	 * @return prevView The previous view
	 */
	protected View getPrevView() {
		System.out.println("Returning to previous menu...");
		return prevView;
	}

}
