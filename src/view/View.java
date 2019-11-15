package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class View {

	public View prevView;

	protected abstract int options(); // options in each menu

	protected abstract void runMenu();

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

	protected void display(View v, View u) {
		u.prevView = v;
		u.runMenu();
	}

	protected View getPrevView() {
		System.out.println("Returning to previous menu...");
		return prevView;
	}

}
