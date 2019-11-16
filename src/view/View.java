package view;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * abstract menu which contains general methods
 */
public abstract class View {

	public View prevView;

	protected abstract int options(); // options in each menu

	protected abstract void runMenu();

	protected int getInput(int Opts) { // error checking for input
		/**
		 * request input from user
		 * @param int Opts
		 * @return int choice if successful, -1 if unsuccessful
		 */
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

	protected void display(View v, View u) {
		/**
		 * display the view
		 * @param View v
		 * @param View u
		 */
		u.prevView = v;
		u.runMenu();
	}

	protected View getPrevView() {
		/**
		 * get previous view
		 * @preturn View previous view
		 */
		System.out.println("Returning to previous menu...");
		return prevView;
	}

}
